/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiBrightcovePlaylist.h"
#import "TiBrightcoveVideo.h"

#import "TiUtils.h"

@implementation TiBrightcovePlaylist
@synthesize playlist;

-(id)_initWithPageContext:(id <TiEvaluator>)context playlist:(BCPlaylist *)playlist_
{
	if (self = [super _initWithPageContext:context]) {
		playlist = [playlist_ retain];
	}
	return self;
}

-(void)_destroy
{
	RELEASE_TO_NIL(playlist);
	[super _destroy];
}

// Handle non-object properties of the playlist
-(NSNumber*)playlistId
{
	return [NSNumber numberWithLongLong:[playlist playlistId]];
}

-(NSNumber*)accountId
{
	return [NSNumber numberWithLongLong:[playlist accountId]];
}

-(NSNumber*)playlistType
{
	return [NSNumber numberWithInt:[playlist playlistType]];
}

-(NSString*)playlistTypeString
{
	return [BCPlaylist getStringForPlaylistType:[playlist playlistType]];
}

// Need to return the video list as TiBrightcoveVideos, not BCVideos
-(NSArray*)videos
{
	NSArray* bcVideos = [playlist videos];
	NSMutableArray* videos = [NSMutableArray array];
	for (BCVideo* video in bcVideos) {
		[videos addObject:[[[TiBrightcoveVideo alloc] _initWithPageContext:[self pageContext] video:video] autorelease]];
	}
	return videos;
}

// Handle everything else - note that we have to be kind of sneaky, due to the lack of a toDictionary:
// like BCVideo has.
-(id)valueForUndefinedKey:(NSString *)key
{
	SEL selector = NSSelectorFromString(key);
	if ([playlist respondsToSelector:selector]) {
		return [playlist performSelector:selector];
	}
	return [super valueForUndefinedKey:key];
}

@end
