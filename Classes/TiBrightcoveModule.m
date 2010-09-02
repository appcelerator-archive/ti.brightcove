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

@implementation TiBrightcoveModule

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

-(void)test:(id)args
{
	BCMediaAPI *bc = [[BCMediaAPI alloc] initWithReadToken:@"YmvFO5RekMdbqKGyT5CnLWzOR-J4Cq2IwpBV08B6vuwiBAUrRYQkmA.."];
	NSError *error = nil;
	BCVideo *video = [bc findVideoById:95654379001 error:&error];
	if (error!=nil)
	{
		NSLog(@"[ERROR] %@",error);
	}
	else 
	{
		BCMoviePlayerController *controller = [[BCMoviePlayerController alloc]init];
		[controller setContentURL:video];
		controller.view.frame = CGRectMake(0, 0, 480.0, 320.0);
		UIViewController *root = [[TiApp app] controller];
		[root.view addSubview:controller.view];
	}

}


@end
