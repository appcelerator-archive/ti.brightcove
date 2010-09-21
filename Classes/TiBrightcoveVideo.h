/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
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
