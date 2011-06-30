# Ti.Brightcove.Video

## Description

A _brightcove_ module object which represents a video.

## Properties

### Ti.Brightcove.Video.name[string] (read-only)

The name of the video

### Ti.Brightcove.Video.videoId[int] (read-only)

The video ID.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes a videoId.

### Ti.Brightcove.Video.referenceId[string] (read-only)

The video reference ID.

### Ti.Brightcove.Video.accountId[int] (read-only)

The account ID that the video belongs to.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes an accountId.

### Ti.Brightcove.Video.shortDescription[string] (read-only)

A short description of the video.

### Ti.Brightcove.Video.longDescription[string] (read-only)

The full description of the video.

### Ti.Brightcove.Video.creationDate[object] (read-only)

A date object representing when the video was created.

### Ti.Brightcove.Video.publishedDate[object] (read-only)

A date object representing when the video was published.

### Ti.Brightcove.Video.lastModifiedDate[object] (read-only)

A date object representing when the video was last modified.

### Ti.Brightcove.Video.tags[array] (read-only)

The video's tags.

### Ti.Brightcove.Video.videoStillURL[string] (read-only)

The URL for a video still frame.

### Ti.Brightcove.Video.thumbnailURL[string] (read-only)

The URL for a video thumbnail.

### Ti.Brightcove.Video.length[int] (read-only)

The length of the video (in seconds).  Internally a 'long long' type, which cannot
be represented accurately in javascript.  This means for exceptionally long videos
the value returned may be incorrect.

### Ti.Brightcove.Video.playsTotal[int] (read-only)

The number of times the video has been played.

### Ti.Brightcove.Video.playsTrailingWeek[int] (read-only)

The number of times the video has been played in the last week.

### Ti.Brightcove.Video.customFields[dictionary] (read-only)

The video's custom fields and their values.

### Ti.Brightcove.Video.itemState[int] (read-only)

A constant representing the current state of the video.