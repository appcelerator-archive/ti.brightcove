# Ti.Brightcove.VideoPlayer

## Description

A _brightcove_ module object which represents a video player.  All properties and
functions which apply to _[Titanium.Media.VideoPlayer][]_ also apply to the Brightcove
media player.  However, the _url_ and _contentURL_ properties now take a
[Ti.Brightcove.Video][] object.

## Properties

### Ti.Brightcove.VideoPlayer.video[object]

The [Ti.Brightcove.Video][] object to play.  Synonym for _url_ and _contentURL_ with
Brightcove players.

### Ti.Brightcove.VideoPlayer.lowBitRate[double]

The low-end bit rate for the video to display.  Value is in bps.

### Ti.Brightcove.VideoPlayer.highBitRate[double]

The high-end bit rate for the video to display.  Value is in bps.

[Ti.Brightcove.Video]: video.html
[Titanium.Media.VideoPlayer]: http://developer.appcelerator.com/apidoc/mobile/latest/Titanium.Media.VideoPlayer-object.html