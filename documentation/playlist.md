# ti.brightcove.Playlist

## Description

A _brightcove_ module object which represents a playlist.

## Reference

## Properties

### ti.brightcove.Playlist.playlistId[int] (read-only)

The playlist ID.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes a playlistId.

### ti.brightcove.Playlist.referenceId[string] (read-only)

The reference ID for the playlist.

### ti.brightcove.Playlist.accountId[int] (read-only)

The account ID that the playlist belongs to.  Internally a 'long long' type, which cannot be represented in javascript,
although the value may be passed to any function which takes an accountId.

### ti.brightcove.Playlist.name[string] (read-only)

The name of the playlist

### ti.brightcove.Playlist.shortDescription[string] (read-only)

A short description of the playlist.

### ti.brightcove.Playlist.videoIds[array] (read-only)

The IDs for the videos contained within the playlist.

### ti.brightcove.Playlist.videos[array] (read-only)

An array of [ti.brightcove.Video][] objects contained in the playlist.

### ti.brightcove.Playlist.playlistType[int] (read-only)

The playlist type, as an integer constant.

### ti.brightcove.Playlist.playlistTypeString[string] (read-only)

The playlist type, as a string.

### ti.brightcove.Playlist.thumbnailURL[string] (read-only)

The URL for a playlist thumbnail.

[ti.brightcove.Video]: video.html