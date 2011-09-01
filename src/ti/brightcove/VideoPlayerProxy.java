/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2011 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */
package ti.brightcove;

import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;

import android.app.Activity;

@Kroll.proxy(creatableInModule = BrightcoveModule.class)
public class VideoPlayerProxy extends TiViewProxy {

	private VideoProxy _video;
	private double _lowRate;
	private double _highRate;

	public VideoPlayerProxy(TiContext tiContext) {
		super(tiContext);
	}

	@Override
	public void handleCreationDict(KrollDict args) {
		super.handleCreationDict(args);

		if (args.containsKey("video")) {
			setVideo((VideoProxy) args.get("video"));
		}
		if (args.containsKey("lowBitRate")) {
			setLowBitRate(args.getDouble("lowBitRate"));
		}
		if (args.containsKey("highBitRate")) {
			setHighBitRate(args.getDouble("highBitRate"));
		}
	}

	private VideoPlayer _player;

	@Override
	public TiUIView createView(Activity activity) {
		_player = new VideoPlayer(this);
		_player.getLayoutParams().autoFillsHeight = true;
		_player.getLayoutParams().autoFillsWidth = true;
		if (_video != null) {
			_player.setVideo(_video.getVideo());
			_video = null;
		}
		if (_player != null) {
			_player.setLowBitRate(_lowRate);
		}
		if (_player != null) {
			_player.setHighBitRate(_highRate);
		}
		return _player;
	}

	@Kroll.setProperty()
	public void setVideo(VideoProxy video) {
		if (_player != null) {
			_player.setVideo(video.getVideo());
		} else {
			_video = video;
		}
	}

	@Kroll.setProperty
	public void setLowBitRate(double rate) {
		if (_player != null) {
			_player.setLowBitRate(rate);
		} else {
			_lowRate = rate;
		}
	}

	@Kroll.setProperty
	public void setHighBitRate(double rate) {
		if (_player != null) {
			_player.setHighBitRate(rate);
		} else {
			_highRate = rate;
		}
	}

	@Kroll.method
	public boolean canPause() {
		return _player.getPlayer().canPause();
	}

	@Kroll.method
	public boolean canSeekBackward() {
		return _player.getPlayer().canSeekBackward();
	}

	@Kroll.method
	public boolean canSeekForward() {
		return _player.getPlayer().canSeekForward();
	}

	@Kroll.method
	@Kroll.getProperty
	public int getBufferPercentage() {
		return _player.getPlayer().getBufferPercentage();
	}

	@Kroll.method
	@Kroll.getProperty
	public int getCurrentPosition() {
		return _player.getPlayer().getCurrentPosition();
	}

	@Kroll.method
	@Kroll.getProperty
	public int getDuration() {
		return _player.getPlayer().getDuration();
	}

	@Kroll.method
	@Kroll.getProperty
	public int getHighBitRate() {
		return _player.getPlayer().getHighBitRate();
	}

	@Kroll.method
	@Kroll.getProperty
	public int getLowBitRate() {
		return _player.getPlayer().getLowBitRate();
	}

	@Kroll.method
	@Kroll.getProperty
	public int getRegion() {
		return _player.getPlayer().getRegion().ordinal();
	}

	@Kroll.method
	public boolean isPlaying() {
		return _player.getPlayer().isPlaying();
	}

	@Kroll.method
	public void logEnabled(boolean pLogEnabled) {
		_player.getPlayer().logEnabled(pLogEnabled);
	}

	@Kroll.method
	public void pause() {
		_player.getPlayer().pause();
	}

	@Kroll.method
	public void resetBitRateRange() {
		_player.getPlayer().resetBitRateRange();
	}

	@Kroll.method
	public void rotateOrientation() {
		_player.getPlayer().rotateOrientation();
	}

	@Kroll.method
	public void seekTo(int pPos) {
		_player.getPlayer().seekTo(pPos);
	}

	@Kroll.method
	public void start() {
		_player.getPlayer().start();
	}

	@Kroll.method
	public void stop() {
		_player.getPlayer().stop();
	}

}
