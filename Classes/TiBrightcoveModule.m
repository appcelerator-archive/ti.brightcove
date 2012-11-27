/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiBrightcoveModule.h"
#import "TiBase.h"
#import "TiHost.h"
#import "TiUtils.h"
#import "TiApp.h"
#import "BCMediaAPI.h"
#import "BCVideo.h"
#import "BCMoviePlayerController.h"
#import "BCObject.h"
#import "BCConfigManager.h"
#import "TiBrightcoveVideo.h"
#import "TiBrightcovePlaylist.h"

@implementation TiBrightcoveModule
@synthesize readToken;

#pragma mark Internal

// this is generated for your module, please do not change it
-(id)moduleGUID
{
	return @"2c94b236-9c45-4202-835b-85bb13b261ae";
}

// this is generated for your module, please do not change it
-(NSString*)moduleId
{
	return @"ti.brightcove";
}

-(void)shutdown:(id)sender
{
    RELEASE_TO_NIL(brightcove);
    RELEASE_TO_NIL(readToken);
}

-(BCMediaAPI*)api
{
    if (brightcove == nil) {
        if (readToken == nil) {
            [self throwException:@"Must set 'readToken' property before calling API functions" 
                       subreason:nil
                        location:CODELOCATION];
        }
        brightcove = [[BCMediaAPI alloc] initWithReadToken:readToken];
		
		// HTTP is used for streaming, apparently.  But there might be cases where this doesn't work - we'll
		// have to investigate, eventually?
		[brightcove setMediaDeliveryType:BCMediaDeliveryTypeHTTP];
    }
    return brightcove;
}

// TODO: We could clean up this code (but make it slightly slower!) by using NSInvocations and a common structure for everything.
#pragma mark Public API

#pragma mark Video APIs

-(id)getVideos:(id)arg
{
	ENSURE_SINGLE_ARG_OR_NIL(arg, NSDictionary);

	NSError* error;
	BCItemCollection* collection;
	if (arg == nil) {
		collection = [[self api] findAllVideos:&error];
	}
	else {
		int pageSize = [TiUtils intValue:@"pageSize" properties:arg def:100];
		int pageNumber = [TiUtils intValue:@"pageNumber" properties:arg def:0];
		BCSortByType sortType = [TiUtils intValue:@"sortBy" properties:arg def:BCSortByTypePublishDate];
		BCSortOrderType sortOrder = [TiUtils intValue:@"sortOrder" properties:arg def:BCSortOrderTypeASC];
		NSArray* videoFields = [arg valueForKey:@"videoFields"];
		NSArray* customFields = [arg valueForKey:@"customFields"];
		
		collection = [[self api] findAllVideos:pageSize
									pageNumber:pageNumber
										sortBy:sortType
									 sortOrder:sortOrder 
								  getItemCount:YES
								   videoFields:videoFields
								  customFields:customFields
										 error:&error];
	}
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* videoObjects = [NSMutableArray array];
	for (id video in [collection items]) {
		[videoObjects addObject:[[[TiBrightcoveVideo alloc] _initWithPageContext:[self pageContext] video:video] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
																				videoObjects,@"videos",
																				[NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
																				[NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	return resultDictionary;
}

-(id)getVideosByIds:(id)args
{
	ENSURE_SINGLE_ARG(args, NSDictionary);
	NSArray* videoIds = [args objectForKey:@"ids"];
	if (videoIds == nil) {
		[self throwException:@"getVideosByIds() requires 'ids' property in argument"
				   subreason:nil
					location:CODELOCATION];
	}
	
	NSError* error;
	BCItemCollection* collection = [[self api] findVideosByIds:videoIds
												   videoFields:[args valueForKey:@"videoFields"]
												  customFields:[args valueForKey:@"customFields"]
														 error:&error];
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* videoObjects = [NSMutableArray array];
	for (id video in [collection items]) {
		[videoObjects addObject:[[[TiBrightcoveVideo alloc] _initWithPageContext:[self pageContext] video:video] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  videoObjects,@"videos",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	
	return resultDictionary;
}

-(id)getRelatedVideos:(id)args
{
	ENSURE_SINGLE_ARG(args, NSDictionary);
	NSNumber* videoId = [args objectForKey:@"id"];
	if (videoId == nil) {
		[self throwException:@"getRelatedVideos() requires 'id' property in argument"
				   subreason:nil
					location:CODELOCATION];
	}
	
	NSError* error;
	int pageSize = [TiUtils intValue:@"pageSize" properties:args def:100];
	int pageNumber = [TiUtils intValue:@"pageNumber" properties:args def:0];
	
	BCItemCollection* collection = [[self api] findRelatedVideos:[videoId longLongValue] 
													 referenceId:[args objectForKey:@"referenceId"]
														pageSize:pageSize
													  pageNumber:pageNumber
													getItemCount:YES
													 videoFields:[args objectForKey:@"videoFields"]
													customFields:[args objectForKey:@"customFields"]
														   error:&error];
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* videoObjects = [NSMutableArray array];
	for (id video in [collection items]) {
		[videoObjects addObject:[[[TiBrightcoveVideo alloc] _initWithPageContext:[self pageContext] video:video] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  videoObjects,@"videos",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	
	return resultDictionary;
}

-(id)getVideosByReferenceIds:(id)args
{
	ENSURE_SINGLE_ARG(args, NSDictionary);
	NSArray* videoIds = [args objectForKey:@"referenceIds"];
	if (videoIds == nil) {
		[self throwException:@"getVideosByReferenceIds() requires 'referenceIds' property in argument"
				   subreason:nil
					location:CODELOCATION];
	}
	
	NSError* error;
	BCItemCollection* collection = [[self api] findVideosByReferenceIds:videoIds
												   videoFields:[args valueForKey:@"videoFields"]
												  customFields:[args valueForKey:@"customFields"]
														 error:&error];
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* videoObjects = [NSMutableArray array];
	for (id video in [collection items]) {
		[videoObjects addObject:[[[TiBrightcoveVideo alloc] _initWithPageContext:[self pageContext] video:video] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  videoObjects,@"videos",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	
	return resultDictionary;
}

-(id)getModifiedVideos:(id)args
{
	ENSURE_SINGLE_ARG(args, NSDictionary);
	NSDate* date = [args objectForKey:@"date"];
	if (date == nil) {
		[self throwException:@"getModifiedVideos() requires 'date' property in argument"
				   subreason:nil
					location:CODELOCATION];
	}
	
	NSError* error;
	int pageSize = [TiUtils intValue:@"pageSize" properties:args def:100];
	int pageNumber = [TiUtils intValue:@"pageNumber" properties:args def:0];
	BCSortByType sortType = [TiUtils intValue:@"sortBy" properties:args def:BCSortByTypePublishDate];
	BCSortOrderType sortOrder = [TiUtils intValue:@"sortOrder" properties:args def:BCSortOrderTypeASC];
	
	BCItemCollection* collection = [[self api] findModifiedVideos:date
														  filters:[args objectForKey:@"filters"]
														 pageSize:pageSize
													   pageNumber:pageNumber
														   sortBy:sortType
														sortOrder:sortOrder
													 getItemCount:YES
													  videoFields:[args objectForKey:@"videoFields"]
													 customFields:[args objectForKey:@"customFields"]
															error:&error];
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* videoObjects = [NSMutableArray array];
	for (id video in [collection items]) {
		[videoObjects addObject:[[[TiBrightcoveVideo alloc] _initWithPageContext:[self pageContext] video:video] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  videoObjects,@"videos",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	
	return resultDictionary;
}

-(id)getVideosByText:(id)args
{
	NSLog(@"[WARN] BrightcoveModule getVideosByText has been deprecated.");
	ENSURE_SINGLE_ARG(args, NSDictionary);
	NSString* text = [args objectForKey:@"text"];
	if (text == nil) {
		[self throwException:@"getVideosByText() requires 'text' property in argument"
				   subreason:nil
					location:CODELOCATION];
	}
	
	NSError* error;
	int pageSize = [TiUtils intValue:@"pageSize" properties:args def:100];
	int pageNumber = [TiUtils intValue:@"pageNumber" properties:args def:0];
	
	BCItemCollection* collection = [[self api] findVideosByText:text
														 pageSize:pageSize
													   pageNumber:pageNumber
													 getItemCount:YES
													  videoFields:[args objectForKey:@"videoFields"]
													 customFields:[args objectForKey:@"customFields"]
															error:&error];
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* videoObjects = [NSMutableArray array];
	for (id video in [collection items]) {
		[videoObjects addObject:[[[TiBrightcoveVideo alloc] _initWithPageContext:[self pageContext] video:video] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  videoObjects,@"videos",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	
	return resultDictionary;
}

-(id)getVideosByTags:(id)args
{
	NSLog(@"[WARN] BrightcoveModule getVideosByTags has been deprecated.");
	ENSURE_SINGLE_ARG(args, NSDictionary);
	NSArray* andTags = [args objectForKey:@"andTags"];
	NSArray* orTags = [args objectForKey:@"orTags"];
	if (andTags == nil && orTags == nil) {
		[self throwException:@"getVideosByTags() requires at least one of 'andTags' or 'orTags' in argument"
				   subreason:nil
					location:CODELOCATION];
	}
	
	NSError* error;
	int pageSize = [TiUtils intValue:@"pageSize" properties:args def:100];
	int pageNumber = [TiUtils intValue:@"pageNumber" properties:args def:0];
	BCSortByType sortType = [TiUtils intValue:@"sortBy" properties:args def:BCSortByTypePublishDate];
	BCSortOrderType sortOrder = [TiUtils intValue:@"sortOrder" properties:args def:BCSortOrderTypeASC];
	
	BCItemCollection* collection = [[self api] findVideosByTags:andTags
														 orTags:orTags
														 pageSize:pageSize
													   pageNumber:pageNumber
														   sortBy:sortType
														sortOrder:sortOrder
													 getItemCount:YES
													  videoFields:[args objectForKey:@"videoFields"]
													 customFields:[args objectForKey:@"customFields"]
															error:&error];
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* videoObjects = [NSMutableArray array];
	for (id video in [collection items]) {
		[videoObjects addObject:[[[TiBrightcoveVideo alloc] _initWithPageContext:[self pageContext] video:video] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  videoObjects,@"videos",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	
	return resultDictionary;
}

#pragma mark Playlist APIs

-(id)getPlaylists:(id)arg
{
	ENSURE_SINGLE_ARG_OR_NIL(arg, NSDictionary);
	NSError* error;
	BCItemCollection* collection;
	if (arg == nil) {
		collection = [[self api] findAllPlaylists:&error];
	}
	else {
		int pageSize = [TiUtils intValue:@"pageSize" properties:arg def:100];
		int pageNumber = [TiUtils intValue:@"pageNumber" properties:arg def:0];
		BCSortByType sortType = [TiUtils intValue:@"sortBy" properties:arg def:BCSortByTypePublishDate];
		BCSortOrderType sortOrder = [TiUtils intValue:@"sortOrder" properties:arg def:BCSortOrderTypeASC];
		NSArray* videoFields = [arg valueForKey:@"videoFields"];
		NSArray* playlistFields = [arg valueForKey:@"playlistFields"];
		NSArray* customFields = [arg valueForKey:@"customFields"];
		
		collection = [[self api] findAllPlaylists:pageSize
									pageNumber:pageNumber
										sortBy:sortType
									 sortOrder:sortOrder 
								  getItemCount:YES
								   videoFields:videoFields
								   playlistFields:playlistFields
								  customFields:customFields
										 error:&error];
	}
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* playlistObjects = [NSMutableArray array];
	for (id playlist in [collection items]) {
		[playlistObjects addObject:[[[TiBrightcovePlaylist alloc] _initWithPageContext:[self pageContext] playlist:playlist] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  playlistObjects,@"playlists",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	return resultDictionary;	
}

-(id)getPlaylistsByIds:(id)args
{
	ENSURE_SINGLE_ARG(args, NSDictionary);
	NSArray* playlistIds = [args objectForKey:@"ids"];
	if (playlistIds == nil) {
		[self throwException:@"getPlaylistsByIds() requires 'ids' property in argument"
				   subreason:nil
					location:CODELOCATION];
	}
	
	NSError* error;
	BCItemCollection* collection = [[self api] findPlaylistsByIds:playlistIds
												   videoFields:[args valueForKey:@"videoFields"]
												playlistFields:[args valueForKey:@"playlistFields"]
												  customFields:[args valueForKey:@"customFields"]
														 error:&error];
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* playlistObjects = [NSMutableArray array];
	for (id playlist in [collection items]) {
		[playlistObjects addObject:[[[TiBrightcovePlaylist alloc] _initWithPageContext:[self pageContext] playlist:playlist] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  playlistObjects,@"playlists",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	
	return resultDictionary;
}

-(id)getPlaylistsByReferenceIds:(id)args
{
	ENSURE_SINGLE_ARG(args, NSDictionary);
	NSArray* playlistIds = [args objectForKey:@"referenceIds"];
	if (playlistIds == nil) {
		[self throwException:@"getPlaylistsByReferenceIds() requires 'referenceIds' property in argument"
				   subreason:nil
					location:CODELOCATION];
	}
	
	NSError* error;
	BCItemCollection* collection = [[self api] findPlaylistsByReferenceIds:playlistIds
															videoFields:[args valueForKey:@"videoFields"]
															playlistFields:[args valueForKey:@"playlistFields"]
														   customFields:[args valueForKey:@"customFields"]
																  error:&error];
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* playlistObjects = [NSMutableArray array];
	for (id playlist in [collection items]) {
		[playlistObjects addObject:[[[TiBrightcovePlaylist alloc] _initWithPageContext:[self pageContext] playlist:playlist] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  playlistObjects,@"playlists",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	
	return resultDictionary;
}

-(id)getPlaylistsByPlayerId:(id)args
{
	ENSURE_SINGLE_ARG(args, NSDictionary);
	NSNumber* playerId = [args objectForKey:@"playerId"];
	if (playerId == nil) {
		[self throwException:@"getPlaylistsByPlayerId() requires 'playerId' property in argument"
				   subreason:nil
					location:CODELOCATION];
	}
	
	NSError* error;
	int pageSize = [TiUtils intValue:@"pageSize" properties:args def:100];
	int pageNumber = [TiUtils intValue:@"pageNumber" properties:args def:0];
	
	BCItemCollection* collection = [[self api] findPlaylistsForPlayerId:[playerId longLongValue]
														 pageSize:pageSize
													   pageNumber:pageNumber
													 getItemCount:YES
													  videoFields:[args objectForKey:@"videoFields"]
												   playlistFields:[args objectForKey:@"playlistFields"]
													 customFields:[args objectForKey:@"customFields"]
															error:&error];
	
	if (collection == nil) {
		[self throwException:@"Brightcove error" 
				   subreason:[[self api] getErrorsAsString:error]
					location:CODELOCATION];
	}
	
	NSMutableArray* playlistObjects = [NSMutableArray array];
	for (id playlist in [collection items]) {
		[playlistObjects addObject:[[[TiBrightcovePlaylist alloc] _initWithPageContext:[self pageContext] playlist:playlist] autorelease]];
	}
	
	NSDictionary* resultDictionary = [NSDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithLongLong:[collection totalCount]],@"totalCount",
									  playlistObjects,@"playlists",
									  [NSNumber numberWithInt:[collection pageNumber]], @"pageNumber",
									  [NSNumber numberWithInt:[collection pageSize]], @"pageSize", nil];
	
	return resultDictionary;
}

#pragma mark Properties

-(void)setReadToken:(NSString*)token
{
    if (readToken != token) {
        [readToken release];
        readToken = [token retain];
    
        // Have to reset the media API object when required
        if (brightcove != nil) {
            [brightcove setReadToken:readToken];
        }
    }
}

-(NSNumber*)deliveryType
{
	return [NSNumber numberWithInt:[[self api] mediaDeliveryType]];
}

-(void)setDeliveryType:(NSNumber*)type
{
	[[self api] setMediaDeliveryType:[type intValue]];
}

-(NSNumber*)region 
{
    return [NSNumber numberWithInt:[[BCConfigManager sharedInstance] region]];
}

-(void)setRegion:(NSNumber*)region
{
    [[BCConfigManager sharedInstance] setRegion:[region intValue]];
}

-(NSNumber*)logging
{
    return [NSNumber numberWithBool:[[BCConfigManager sharedInstance] logging]];
}

-(void)setLogging:(NSNumber*)log
{
    [[BCConfigManager sharedInstance] setLogging:[log boolValue]];
}

MAKE_SYSTEM_PROP(SORT_BY_PUBLISH_DATE, BCSortByTypePublishDate);
MAKE_SYSTEM_PROP(SORT_BY_CREATION_DATE, BCSortByTypeCreationDate);
MAKE_SYSTEM_PROP(SORT_BY_MODIFIED_DATE, BCSortByTypeModifiedDate);
MAKE_SYSTEM_PROP(SORT_BY_PLAYS_TOTAL, BCSortByTypePlaysTotal);
MAKE_SYSTEM_PROP(SORT_BY_PLAYS_TRAILING_WEEK, BCSortByTypePlaysTrailingWeek);

MAKE_SYSTEM_PROP(ORDER_ASCENDING, BCSortOrderTypeASC);
MAKE_SYSTEM_PROP(ORDER_DESCENDING, BCSortOrderTypeDESC);

MAKE_SYSTEM_PROP(STATE_ACTIVE, BCItemStateActive);
MAKE_SYSTEM_PROP(STATE_INACTIVE, BCItemStateInactive);
MAKE_SYSTEM_PROP(STATE_DELETED, BCItemStateDeleted);

MAKE_SYSTEM_PROP(FREE, BCEconomicsFree);
MAKE_SYSTEM_PROP(AD_SUPPORTED, BCEconomicsAdSupported);

MAKE_SYSTEM_PROP(CODEC_SORENSON, BCVideoCodecSORENSON);
MAKE_SYSTEM_PROP(CODEC_ON2, BCVideoCodecON2);
MAKE_SYSTEM_PROP(CODEC_H264, BCVideoCodecH264);

MAKE_SYSTEM_PROP(PLAYLIST_OLDEST_TO_NEWEST, BCPlaylistTypeOldestToNewest);
MAKE_SYSTEM_PROP(PLAYLIST_NEWEST_TO_OLDEST, BCPlaylistTypeNewestToOldest);
MAKE_SYSTEM_PROP(PLAYIST_ALPHABETICAL, BCPlaylistTypeAlphabetical);
MAKE_SYSTEM_PROP(PLAYLIST_PLAYS_TOTAL, BCPlaylistTypePlaysTotal);
MAKE_SYSTEM_PROP(PLAYLIST_PLAYS_TRAILING_WEEK, BCPlaylistTypePlaysTrailingWeek);
MAKE_SYSTEM_PROP(PLAYLIST_EXPLICIT, BCPlaylistTypeExplicit);
MAKE_SYSTEM_PROP(PLAYLIST_START_OLDEST_TO_NEWEST, BCPlaylistTypeStartDateOldestToNewest);
MAKE_SYSTEM_PROP(PLAYLIST_START_NEWEST_TO_OLDEST, BCPlaylistTypeStartDateNewestToOldest);

MAKE_SYSTEM_PROP(IMAGE_THUMBNAIL, BCImageTypeThumbnail);
MAKE_SYSTEM_PROP(iMAGE_STILL, BCImageTypeVideoStill);

MAKE_SYSTEM_PROP(CUE_AD, BCCuePointTypeAd);
MAKE_SYSTEM_PROP(CUE_CODE, BCCuePointTypeCode);
MAKE_SYSTEM_PROP(CUE_CHAPTER, BCCUePointTypeChapter); // Typo in the header

MAKE_SYSTEM_PROP(REGION_US, BCRegionUS);
MAKE_SYSTEM_PROP(REGION_JP, BCRegionJP);

MAKE_SYSTEM_PROP(DELIVERY_DEFAULT, BCMediaDeliveryTypeDefault);
MAKE_SYSTEM_PROP(DELIVERY_HTTP,  BCMediaDeliveryTypeHTTP);

@end
