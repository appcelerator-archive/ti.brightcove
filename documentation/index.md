# Ti.Brightcove Module

## Description
The Ti.Brightcove module provides access to the retrieval and playback of videos from
the Brightcove media service.

## Accessing the Ti.Brightcove Module
To access this module from JavaScript, you would do the following:

	Titanium.Brightcove = Ti.Brightcove = require("Ti.Brightcove");

## Functions

### Ti.Brightcove.createVideoPlayer({...})
Creates and returns a [Ti.Brightcove.VideoPlayer][] object.

### Ti.Brightcove.getVideos([{...}])
Gets videos from the Brightcove service.  Takes an optional dictionary argument,
with the following (all optional) properties:

* pageSize[int]: The number of videos to include on a returned page
* pageNumber[int]: The page number to return
* sortType[int]: The type of sorting to perform
* sortOrder[int]: The order to sort in
* videoFields[array]: An array of fields which should match the returned videos
* customFields[array]: An array of custom field {name:value} pairs which should match the returned videos.

Returns the following dictionary:

* totalCount[int]: The total number of videos returned (NOTE: This value may be incorrect)
* videos[array]: The [Ti.Brightcove.Video][] objects returned by the query
* pageNumber[int]: The page number returned by the operation
* pageSize[int]: The number of videos in the page

### Ti.Brightcove.getVideosByIds({...})
Gets videos with the specified IDs from the Brightcove service.  Takes a dictionary argument,
with the following properties:

* ids[array]: The video IDs to retrieve
* videoFields[array] (optional): An array of fields which should match the returned videos
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned videos.

Returns the following dictionary:

* totalCount[int]: The total number of videos returned (NOTE: This value may be incorrect)
* videos[array]: The [Ti.Brightcove.Video][] objects returned by the query
* pageNumber[int]: The page number returned by the operation (NOTE: This value may not be defined for this operation)
* pageSize[int]: The number of videos in the page (NOTE: This value may not be defined for this operation)

### Ti.Brightcove.getRelatedVideos({...})
Gets videos related to a single video from the Brightcove service, which is done by
combining description information from the provided video and matching it against other
videos' description, tags, and name.  Takes a dictionary argument, with the following properties:

* id[int]: The ID of the video to find related media for
* referenceId[string] (optional): The reference ID of the video to find media for
* pageSize[int] (optional): The number of videos to include on a returned page
* pageNumber[int] (optional): The page number to return
* videoFields[array] (optional): An array of fields which should match the returned videos
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned videos.

Returns the following dictionary:

* totalCount[int]: The total number of videos returned (NOTE: This value may be incorrect)
* videos[array]: The [Ti.Brightcove.Video][] objects returned by the query
* pageNumber[int]: The page number returned by the operation
* pageSize[int]: The number of videos in the page

### Ti.Brightcove.getVideosByReferenceIds({...})
Gets videos with the specified reference IDs from the Brightcove service.  Takes a dictionary argument,
with the following properties:

* referenceIds[array]: The reference IDs to retrieve
* videoFields[array] (optional): An array of fields which should match the returned videos
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned videos.

Returns the following dictionary:

* totalCount[int]: The total number of videos returned (NOTE: This value may be incorrect)
* videos[array]: The [Ti.Brightcove.Video][] objects returned by the query
* pageNumber[int]: The page number returned by the operation (NOTE: This value may not be defined for this operation)
* pageSize[int]: The number of videos in the page (NOTE: This value may not be defined for this operation)

### Ti.Brightcove.getVideosByUserId({...})
Gets videos for the specified user ID from the Brightcove service.  Takes a dictionary argument
with the following properties:

* userId[string]: The user ID to retrieve videos for
* pageSize[int] (optional): The number of videos to include on a returned page
* pageNumber[int] (optional): The page number to return
* sortType[int] (optional): The type of sorting to perform
* sortOrder[int] (optional): The order to sort in
* videoFields[array] (optional): An array of fields which should match the returned videos
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned videos.

Returns the following dictionary:

* totalCount[int]: The total number of videos returned (NOTE: This value may be incorrect)
* videos[array]: The [Ti.Brightcove.Video][] objects returned by the query
* pageNumber[int]: The page number returned by the operation
* pageSize[int]: The number of videos in the page

### Ti.Brightcove.getModifiedVideos({...})
Gets videos from the Brightcove service that were modified after the specified time.  Takes a dictionary argument,
with the following properties:

* date[object]: The date videos were modified after
* filters[array] (optional): An array of video state filters. Use the constants starting with "STATE\_"
* pageSize[int] (optional): The number of videos to include on a returned page
* pageNumber[int] (optional): The page number to return
* sortType[int] (optional): The type of sorting to perform
* sortOrder[int] (optional): The order to sort in
* videoFields[array] (optional): An array of fields which should match the returned videos
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned videos.

Returns the following dictionary:

* totalCount[int]: The total number of videos returned (NOTE: This value may be incorrect)
* videos[array]: The [Ti.Brightcove.Video][] objects returned by the query
* pageNumber[int]: The page number returned by the operation
* pageSize[int]: The number of videos in the page

### Ti.Brightcove.getVideosByText({...})
Gets videos from the Brightcove service whose name or description contains the provided text.
Takes a dictionary argument, with the following properties:

* text[string]: The text to match
* pageSize[int] (optional): The number of videos to include on a returned page
* pageNumber[int] (optional): The page number to return
* videoFields[array] (optional): An array of fields which should match the returned videos
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned videos.

Returns the following dictionary:

* totalCount[int]: The total number of videos returned (NOTE: This value may be incorrect)
* videos[array]: The [Ti.Brightcove.Video][] objects returned by the query
* pageNumber[int]: The page number returned by the operation (NOTE: This value may not be defined for this operation)
* pageSize[int]: The number of videos in the page (NOTE: This value may not be defined for this operation)

### Ti.Brightcove.getVideosByTags({...})
Gets videos from the Brightcove service with the matching tags.  Takes a dictionary argument,
with the following properties:

* andTags[array]: An array of tags.  Matched videos MUST contain ALL of these tags.
* orTags[array]: An array of tags.  Matched videos MAY contain ONE OR MORE of these tags.
* pageSize[int] (optional): The number of videos to include on a returned page
* pageNumber[int] (optional): The page number to return
* sortType[int] (optional): The type of sorting to perform
* sortOrder[int] (optional): The order to sort in
* videoFields[array] (optional): An array of fields which should match the returned videos
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned videos.

Returns the following dictionary:

* totalCount[int]: The total number of videos returned (NOTE: This value may be incorrect)
* videos[array]: The [Ti.Brightcove.Video][] objects returned by the query
* pageNumber[int]: The page number returned by the operation
* pageSize[int]: The number of videos in the page

### Ti.Brightcove.getPlaylists([{...}])
Gets playlists from the Brightcove service.  Takes an optional dictionary argument,
with the following (all optional) properties:

* pageSize[int]: The number of videos to include on a returned page
* pageNumber[int]: The page number to return
* sortType[int]: The type of sorting to perform
* sortOrder[int]: The order to sort in
* videoFields[array]: An array of fields which should match the returned videos
* playlistFields[array]: An array of fields which should match the returned playlists
* customFields[array]: An array of custom field {name:value} pairs which should match the returned playlists.

Returns the following dictionary:

* totalCount[int]: The total number of playlists returned (NOTE: This value may be incorrect)
* playlists[array]: The [Ti.Brightcove.Playlist][] objects returned by the query
* pageNumber[int]: The page number returned by the operation
* pageSize[int]: The number of playlists in the page

### Ti.Brightcove.getPlaylistsByIds({...})
Gets playlists with the specified IDs from the Brightcove service.  Takes a dictionary argument,
with the following properties:

* ids[array]: The playlist IDs to retrieve.
* videoFields[array] (optional): An array of fields which should match the returned videos
* playlistFields[array] (optional): An array of fields which should match the returned playlists
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned playlists.

Returns the following dictionary:

* totalCount[int]: The total number of playlists returned (NOTE: This value may be incorrect)
* playlists[array]: The [Ti.Brightcove.Playlist][] objects returned by the query
* pageNumber[int]: The page number returned by the operation (NOTE: This value may not be defined for this operation)
* pageSize[int]: The number of playlists in the page (NOTE: This value may not be defined for this operation)

### Ti.Brightcove.getPlaylistsByReferenceIds({...})
Gets playlists with the specified reference IDs from the Brightcove service.  Takes a dictionary argument,
with the following properties:

* referenceIds[array]: The reference IDs to retrieve
* videoFields[array] (optional): An array of fields which should match the returned videos
* playlistFields[array] (optional): An array of fields which should match the returned playlists
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned playlists.

Returns the following dictionary:

* totalCount[int]: The total number of playlists returned (NOTE: This value may be incorrect)
* playlists[array]: The [Ti.Brightcove.Playlist][] objects returned by the query
* pageNumber[int]: The page number returned by the operation (NOTE: This value may not be defined for this operation)
* pageSize[int]: The number of playlists in the page (NOTE: This value may not be defined for this operation)

### Ti.Brightcove.getPlaylistsByPlayerId({...})
Gets playlists for the given player ID from the Brightcove service.  Takes a dictionary argument,
with the following properties:

* playerId[int]: The player ID
* pageSize[int] (optional): The number of playlists to include on a returned page
* pageNumber[int] (optional): The page number to return
* videoFields[array] (optional): An array of fields which should match the returned videos
* playlistFields[array] (optional): An array of fields which should match the returned playlists
* customFields[array] (optional): An array of custom field {name:value} pairs which should match the returned playlists.

Returns the following dictionary:

* totalCount[int]: The total number of playlists returned (NOTE: This value may be incorrect)
* playlists[array]: The [Ti.Brightcove.Playlist][] objects returned by the query
* pageNumber[int]: The page number returned by the operation
* pageSize[int]: The number of playlists in the page

## Properties

### Ti.Brightcove.readToken
The read token string provided by Brightcove for access to the service.  Must be set
before calling any module functions.

### Ti.Brightcove.deliveryType
The delivery type to use for the Brightcove service.

### Ti.Brightcove.region
The region that Brightcove is operating in.

### Ti.Brightcove.logging
Flag to turn internal Brightcove logging on and off.  Useful for debugging.

## Constants

### Ti.Brightcove.SORT_BY_PUBLISH_DATE
A constant indicating that a returned list should be sorted by publication date.

### Ti.Brightcove.SORT_BY_CREATION_DATE
A constant indicating that a returned list should be sorted by creation date.

### Ti.Brightcove.SORT_BY_MODIFIED_DATE
A constant indicating that a returned list should be sorted by modification date.

### Ti.Brightcove.SORT_BY_PLAYS_TOTAL
A constant indicating that a returned list should be sorted by the total number of plays.

### Ti.Brightcove.SORT_BY_PLAYS_TRAILING_WEEK
A constant indicating that a returned list should be sorted by the total number of plays over the previous week.

### Ti.Brightcove.ORDER_ASCENDING
A constant indicating that a returned list should be sorted in ascending order.

### Ti.Brightcove.ORDER_DESCENDING
A constant indicating that a returned list should be sorted in descending order.

### Ti.Brightcove.STATE_ACTIVE
Represents a video in the 'active' state.

### Ti.Brightcove.STATE_INACTIVE
Represents a video in the 'inactive' state.

### Ti.Brightcove.STATE_DELETED
Represents a video that has been deleted from the service.

### Ti.Brightcove.PLAYLIST_OLDEST_TO_NEWEST
Represents a playlist type that is ordered oldest to newest.

### Ti.Brightcove.PLAYLIST_NEWEST_TO_OLDEST
Represents a playlist type that is ordered newest to oldest.

### Ti.Brightcove.PLAYLIST_ALPHABETICAL
Represents a playlist type that is ordered alphabetically.

### Ti.Brightcove.PLAYLIST_PLAYS_TOTAL
Represents a playlist type that is ordered by the total number of plays.

### Ti.Brightcove.PLAYLIST_PLAYS_TRAILING_WEEK
Represents a playlist type that is ordered by the number of plays in the previous week.

### Ti.Brightcove.PLAYLIST_EXPLICIT
Represents a playlist with explicit, creator-defined ordering.

### Ti.Brightcove.PLAYLIST_START_OLDEST_TO_NEWEST
Represents a playlist ordered by scheduled start times, oldest to newest.

### Ti.Brightcove.PLAYLIST_START_NEWEST_TO_OLDEST
Represents a playlist ordered by scheduled start times, newest to oldest.

### Ti.Brightcove.REGION_US
Sets the brightcove module to use the US region.

### Ti.Brightcove.REGION_JP
Sets the brightcove module to use the Japan region.

### Ti.Brightcove.DELIVERY_DEFAULT
Sets the brightcove module to use the default delivery type.  Note that this is
generally not suitable for streaming.

### Ti.Brightcove.DELIVERY_HTTP
Sets the brightcove module to use HTTP delivery.  This is the delivery value
that should generally be used for streaming.

## Usage
See example.

## Author
Stephen Tramer

## Feedback and Support
Please direct all questions, feedback, and concerns to [info@appcelerator.com](mailto:info@appcelerator.com?subject=iOS%20Brightcove%20Module).

## License
Copyright(c) 2010-2011 by Appcelerator, Inc. All Rights Reserved. Please see the LICENSE file included in the distribution for further details.

[Ti.Brightcove.Video]: video.html
[Ti.Brightcove.Playlist]: playlist.html
[Ti.Brightcove.VideoPlayer]: videoPlayer.html