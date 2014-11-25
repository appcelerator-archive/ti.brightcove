/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */

#import "TiBrightcoveVideoPlayerProxy.h"
#import "BCMoviePlayerController.h"
#import "TiUtils.h"
#import "TiBrightcoveVideo.h"

NSArray* brightcovePlayerKeys = nil;

@implementation TiBrightcoveVideoPlayerProxy

-(NSArray*)keySequence
{
	if (brightcovePlayerKeys == nil) {
		brightcovePlayerKeys = [[NSArray alloc] initWithObjects:@"lowBitRate",@"highBitRate",@"url",@"contentURL",@"video",nil];
	}
	return brightcovePlayerKeys;
}

-(void)_destroy
{
	RELEASE_TO_NIL(lowBitRate);
	RELEASE_TO_NIL(highBitRate);
	RELEASE_TO_NIL(video);
	[super _destroy];
}

-(void)setLowBitRate:(NSNumber*)bitRate
{
	if (lowBitRate != bitRate) {
		[lowBitRate release];
		lowBitRate = [bitRate retain];
		
		if (movie != nil) {
			[(BCMoviePlayerController*)movie searchForRenditionsBetweenLowBitRate:lowBitRate andHighBitRate:highBitRate];
		}
	}
}

-(void)setHighBitRate:(NSNumber*)bitRate
{
	if (highBitRate != bitRate) {
		[highBitRate release];
		highBitRate = [bitRate retain];
		
		if (movie != nil) {
			[(BCMoviePlayerController*)movie searchForRenditionsBetweenLowBitRate:lowBitRate andHighBitRate:highBitRate];
		}
	}
}

-(MPMoviePlayerController*)player
{
	[playerLock lock];
	if (movie == nil) {
		if (video == nil) {
			[playerLock unlock];
			return nil;
		}
		movie = [[BCMoviePlayerController alloc] initWithContentURL:video
								   searchForRenditionWithLowBitRate:lowBitRate
													 andHighBitRate:highBitRate];
		[self configurePlayer];
	}
	[playerLock unlock];
	return movie;
}

-(void)setVideo:(id)video_
{
	ENSURE_SINGLE_ARG(video_, TiBrightcoveVideo);
	ENSURE_UI_THREAD(setVideo, video_);
	if (video != [video_ video]) {
		RELEASE_TO_NIL(video);
		video = [[video_ video] retain];
	}
	
	if (movie != nil) {
		[self restart];
	}
	else {
		[self player];
	}
}

-(void)play:(id)args
{
	ENSURE_UI_THREAD(play,args);
	
	if (video == nil)
	{
		[self throwException:TiExceptionInvalidType
				   subreason:@"Tried to play brightcove player without a valid 'video' property"
					location:CODELOCATION];
	}
	
	if (playing)
	{
		[self stop:nil];
	}
	
	playing = YES;
	
	[[self player] play];
}

-(void)setUrl:(id)video_
{
	NSLog(@"[WARN] Use 'video' when setting video for Brightcove players");
	[self setVideo:video_];
}

-(void)setContentURL:(id)video_
{
	NSLog(@"[WARN] Use 'video' when setting video for Brightcove players");	
	[self setVideo:video_];
}


@end
