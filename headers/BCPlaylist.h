/*
 Permission is hereby granted, free of charge, to any person
 obtaining a copy of this software and associated documentation
 files (the "Software"), to deal in the Software without
 restriction, including without limitation the rights to use,
 copy, modify, merge, publish, distribute, sublicense, and/or
 sell copies of the Software, and to permit persons to whom the
 Software is furnished to do so, subject to the following
 conditions:
 
 1.  The permission granted herein does not extend to commercial use of
 the Software by entities primarily engaged in providing online video
 and related services; and
 
 2.  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

#import <Foundation/Foundation.h>
#import "BCVideo.h"

/**
 @brief The BCPlaylist model object 
 
 This object contains properties describing a video playlist in the Brightcove system.
 It is returned either singly or as part of the items array of a BCItemCollection from 
 playlist-related API calls.
 
 You do not typically instantiate a BCPlaylist object in your code.
 */
@interface BCPlaylist : NSObject 
{
	double playlistId;
	NSString *referenceId;
	double accountId;
	NSString *name;
	NSString *shortDescription;
	NSArray *videoIds;
	NSArray *videos;
	BCPlaylistType playlistType;
	NSString *thumbnailURL;
}

@property (nonatomic, assign) double playlistId;
@property (nonatomic, retain) NSString *referenceId;
@property (nonatomic, assign) double accountId;
@property (nonatomic, retain) NSString *name;
@property (nonatomic, retain) NSString *shortDescription;
@property (nonatomic, retain) NSArray *videoIds;
@property (nonatomic, retain) NSArray *videos;
@property (nonatomic, assign) BCPlaylistType playlistType;
@property (nonatomic, retain) NSString *thumbnailURL;

+ (BCPlaylist *) initFromDictionary:(NSDictionary *) dict;
+ (BCPlaylistType) getPlaylistTypeForString:(NSString *) bcPlaylistType;
+ (NSString *) getStringForPlaylistType:(BCPlaylistType) bcPlaylistType;

@end
