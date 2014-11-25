# Ti.Brightcove.VideoPlayer

## Description
A _brightcove_ module object which represents a video player.

## Properties

### video[object]
The [Ti.Brightcove.Video][] object to play.

### lowBitRate[double]
The low-end bit rate for the video to display. Value is in bps.

### highBitRate[double]
The high-end bit rate for the video to display. Value is in bps.

### bufferPercentage[int]
### currentPosition[int]
### duration[int]
### region[int]

## Methods

### bool canPause()
### bool canSeekBackward()
### bool canSeekForward()
### bool isPlaying()
### void logEnabled(bool enabled)
### void pause()
### void resetBitRateRange()
### void rotateOrientation()
### void seekTo(int pos)
### void start()
### void stop()

[Ti.Brightcove.Video]: video.html
[Titanium.Media.VideoPlayer]: http://developer.appcelerator.com/apidoc/mobile/latest/Titanium.Media.VideoPlayer-object.html