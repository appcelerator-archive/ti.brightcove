/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
#import "TiUIView.h"
#import "TiMediaVideoPlayer.h"

// Brightcove needs no additional functionality on top of the TiMediaVideoPlayer view.
// This is only here in case the newView method in TiMediaVideoPlayer is changed.
@interface TiBrightcoveVideoPlayer : TiMediaVideoPlayer {

}

@end
