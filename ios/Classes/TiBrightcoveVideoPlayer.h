/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiUIView.h"
#import "TiMediaVideoPlayer.h"

// Brightcove needs no additional functionality on top of the TiMediaVideoPlayer view.
// This is only here in case the newView method in TiMediaVideoPlayer is changed.
@interface TiBrightcoveVideoPlayer : TiMediaVideoPlayer {

}

@end
