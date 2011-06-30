# Ti.Brightcove.Playlist

## Description

A _brightcove_ module object which represents a playlist.

## Properties

### Ti.Brightcove.Playlist.playlistId[int] (read-only)

The playlist ID.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes a playlistId.

### Ti.Brightcove.Playlist.referenceId[string] (read-only)

The reference ID for the playlist.

### Ti.Brightcove.Playlist.accountId[int] (read-only)

The account ID that the playlist belongs to.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes an accountId.

### Ti.Brightcove.Playlist.name[string] (read-only)

The name of the playlist

### Ti.Brightcove.Playlist.shortDescription[string] (read-only)

A short description of the playlist.

### Ti.Brightcove.Playlist.videoIds[array] (read-only)

The IDs for the videos contained within the playlist.

### Ti.Brightcove.Playlist.videos[array] (read-only)

An array of [Ti.Brightcove.Video][] objects contained in the playlist.

### Ti.Brightcove.Playlist.playlistType[int] (read-only)

The playlist type, as an integer constant.

### Ti.Brightcove.Playlist.playlistTypeString[string] (read-only)

The playlist type, as a string.

### Ti.Brightcove.Playlist.thumbnailURL[string] (read-only)

The URL for a playlist thumbnail.

[Ti.Brightcove.Video]: video.html