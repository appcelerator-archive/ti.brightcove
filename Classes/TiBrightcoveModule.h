/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiModule.h"
#import "BCMediaAPI.h"
@interface TiBrightcoveModule : TiModule 
{
    BCMediaAPI* 
}

@property(nonatomic, readonly) NSNumber* SORT_BY_PUBLISH_DATE;
@property(nonatomic, readonly) NSNumber* SORT_BY_CREATION_DATE;
@property(nonatomic, readonly) NSNumber* SORT_BY_MODIFIED_DATE;
@property(nonatomic, readonly) NSNumber* SORT_BY_PLAYS_TOTAL;
@property(nonatomic, readonly) NSNumber* SORT_BY_PLAYS_TRAILING_WEEK;

@property(nonatomic, readonly) NSNumber* ORDER_ASCENDING;
@property(nonatomic, readonly) NSNumber* ORDER_DESCENDING;

@property(nonatomic, readonly) NSNumber* STATE_ACTIVE;
@property(nonatomic, readonly) NSNumber* STATE_INACTIVE;
@property(nonatomic, readonly) NSNumber* STATE_DELETED;

@property(nonatomic, readonly) NSNumber* FREE;
@property(nonatomic, readonly) NSNumber* AD_SUPPORTED;

@property(nonatomic, readonly) NSNumber* CODEC_SORENSON;
@property(nonatomic, readonly) NSNumber* CODEC_ON2;
@property(nonatomic, readonly) NSNumber* CODEC_H264;

@property(nonatomic, readonly) NSNumber* PLAYLIST_OLDEST_TO_NEWEST;
@property(nonatomic, readonly) NSNumber* PLAYLIST_NEWEST_TO_OLDEST;
@property(nonatomic, readonly) NSNumber* PLAYIST_ALPHABETICAL;
@property(nonatomic, readonly) NSNumber* PLAYLIST_PLAYS_TOTAL;
@property(nonatomic, readonly) NSNumber* PLAYLIST_PLAYS_TRAILING_WEEK;
@property(nonatomic, readonly) NSNumber* PLAYLIST_EXPLICIT;
@property(nonatomic, readonly) NSNumber* PLAYLIST_START_OLDEST_TO_NEWEST;
@property(nonatomic, readonly) NSNumber* PLAYLIST_START_NEWEST_TO_OLDEST;

@property(nonatomic, readonly) NSNumber* IMAGE_THUMBNAIL;
@property(nonatomic, readonly) NSNumber* iMAGE_STILL;

@property(nonatomic, readonly) NSNumber* CUE_AD;
@property(nonatomic, readonly) NSNumber* CUE_CODE;
@property(nonatomic, readonly) NSNumber* CUE_CHAPTER;

@property(nonatomic, readonly) NSNumber* REGION_US;
@property(nonatomic, readonly) NSNumber* REGION_JP;

@property(nonatomic, readonly) NSNumber* DELIVERY_DEFAULT;
@property(nonatomic, readonly) NSNumber* DELIVERY_HTTP;


@end
