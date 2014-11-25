/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiViewProxy.h"
#import "TiMediaVideoPlayerProxy.h"
#import "BCVideo.h"

// The brightcove video player 'is' a MPMoviePlayerController, so we should offer
// all of the functionality of our video player in addition to the brightcove extras.
@interface TiBrightcoveVideoPlayerProxy : TiMediaVideoPlayerProxy {
@private
	NSNumber* lowBitRate;
	NSNumber* highBitRate;
	BCVideo* video;
}

// Override functions
-(NSArray*)keySequence;
-(MPMoviePlayerController*)player;
-(void)setUrl:(id)video_;
-(void)setContentURL:(id)video_;
-(void)play:(id)args;

@end
