/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiProxy.h"
#import "BCPlaylist.h"

@interface TiBrightcovePlaylist : TiProxy {
	BCPlaylist* playlist;
}
@property (nonatomic, readonly) BCPlaylist* playlist;

-(id)_initWithPageContext:(id<TiEvaluator>)context playlist:(BCPlaylist*)playlist_;

@end
