# ti.brightcove.Video

## Description

A _brightcove_ module object which represents a video.

## Reference

## Properties

### ti.brightcove.Video.name[string] (read-only)

The name of the video

### ti.brightcove.Video.videoId[int] (read-only)

The video ID.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes a videoId.

### ti.brightcove.Video.referenceId[string] (read-only)

The video reference ID.

### ti.brightcove.Video.accountId[int] (read-only)

The account ID that the video belongs to.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes an accountId.

### ti.brightcove.Video.shortDescription[string] (read-only)

A short description of the video.

### ti.brightcove.Video.longDescription[string] (read-only)

The full description of the video.

### ti.brightcove.Video.creationDate[object] (read-only)

A date object representing when the video was created.

### ti.brightcove.Video.publishedDate[object] (read-only)

A date object representing when the video was published.

### ti.brightcove.Video.lastModifiedDate[object] (read-only)

A date object representing when the video was last modified.

### ti.brightcove.Video.tags[array] (read-only)

The video's tags.

### ti.brightcove.Video.videoStillURL[string] (read-only)

The URL for a video still frame.

### ti.brightcove.Video.thumbnailURL[string] (read-only)

The URL for a video thumbnail.

### ti.brightcove.Video.length[int] (read-only)

The length of the video (in seconds).  Internally a 'long long' type, which cannot
be represented accurately in javascript.  This means for exceptionally long videos
the value returned may be incorrect.

### ti.brightcove.Video.playsTotal[int] (read-only)

The number of times the video has been played.

### ti.brightcove.Video.playsTrailingWeek[int] (read-only)

The number of times the video has been played in the last week.

### ti.brightcove.Video.customFields[dictionary] (read-only)

The video's custom fields and their values.

### ti.brightcove.Video.itemState[int] (read-only)

A constant representing the current state of the video.