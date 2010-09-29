# ti.brightcove.VideoPlayer

## Description

A _brightcove_ module object which represents a video player.  All properties and
functions which apply to _Titanium.Media.VideoPlayer_ also apply to the Brightcove
media player.  However, the _url_ and _contentURL_ properties now take a
[ti.brightcove.Video][] object.

## Reference

## Properties

### ti.brightcove.VideoPlayer.video[object]

The [ti.brightcove.Video][] object to play.  Synonym for _url_ and _contentURL_ with
Brightcove players.

### ti.brightcove.VideoPlayer.lowBitRate[double]

The low-end bit rate for the video to display.  Value is in bps.

### ti.brightcove.VideoPlayer.highBitRate[double]

The high-end bit rate for the video to display.  Value is in bps.

[ti.brightcove.Video]: video.html