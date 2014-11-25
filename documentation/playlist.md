# Ti.Brightcove.Playlist

## Description
A _brightcove_ module object which represents a playlist.

## Properties

### playlistId[int] (read-only)
The playlist ID.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes a playlistId.

### referenceId[string] (read-only)
The reference ID for the playlist.

### accountId[int] (read-only)
The account ID that the playlist belongs to.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes an accountId.

### name[string] (read-only)
The name of the playlist

### shortDescription[string] (read-only)
A short description of the playlist.

### videoIds[array] (read-only)
The IDs for the videos contained within the playlist.

### videos[array] (read-only)
An array of [Ti.Brightcove.Video][] objects contained in the playlist.

### playlistType[int] (read-only)
The playlist type, as an integer constant.

### playlistTypeString[string] (read-only)
The playlist type, as a string.

### thumbnailURL[string] (read-only)
The URL for a playlist thumbnail.

[Ti.Brightcove.Video]: video.html