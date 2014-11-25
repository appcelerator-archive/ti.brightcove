# Ti.Brightcove.Video

## Description
A _brightcove_ module object which represents a video.

## Properties

### name[string] (read-only)
The name of the video

### videoId[int] (read-only)
The video ID.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes a videoId.

### referenceId[string] (read-only)
The video reference ID.

### accountId[int] (read-only)
The account ID that the video belongs to.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes an accountId.

### shortDescription[string] (read-only)
A short description of the video.

### longDescription[string] (read-only)
The full description of the video.

### creationDate[object] (read-only)
A date object representing when the video was created.

### publishedDate[object] (read-only)
A date object representing when the video was published.

### lastModifiedDate[object] (read-only)
A date object representing when the video was last modified.

### tags[array] (read-only)
The video's tags.

### videoStillURL[string] (read-only)
The URL for a video still frame.

### thumbnailURL[string] (read-only)
The URL for a video thumbnail.

### length[int] (read-only)
The length of the video (in seconds).  Internally a 'long long' type, which cannot
be represented accurately in javascript.  This means for exceptionally long videos
the value returned may be incorrect.

### playsTotal[int] (read-only)
The number of times the video has been played.

### playsTrailingWeek[int] (read-only)
The number of times the video has been played in the last week.

### customFields[dictionary] (read-only)
The video's custom fields and their values.

### itemState[int] (read-only)
A constant representing the current state of the video.