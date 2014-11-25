/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiProxy.h"
#import "BCVideo.h"

@interface TiBrightcoveVideo : TiProxy {
	BCVideo* video;
	NSDictionary* videoData;
}
@property (nonatomic, readonly) BCVideo* video;

-(id)_initWithPageContext:(id<TiEvaluator>)context video:(BCVideo*)video_;

@end
