/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiBrightcoveVideo.h"

#import "TiUtils.h"

@implementation TiBrightcoveVideo
@synthesize video;

-(id)_initWithPageContext:(id<TiEvaluator>)context video:(BCVideo*)video_
{
	if (self = [super _initWithPageContext:context]) {
		video = [video_ retain];
		videoData = [[BCVideo toDictionary:video] retain]; // Makes returning queries to the object MUCH easier
	}
	return self;
}

-(void)_destroy
{
	RELEASE_TO_NIL(video);
	RELEASE_TO_NIL(videoData);
}

-(id)valueForUndefinedKey:(NSString*)key
{
	id obj = [videoData objectForKey:key];
	if (obj != nil) {
		return obj;
	}
	return [super valueForUndefinedKey:key];
}

@end
