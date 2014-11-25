/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */

package ti.brightcove;

import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.TiApplication;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.view.Gravity;

import com.brightcove.mobile.android.BCPlayerView;
import com.brightcove.mobile.android.BCPlayerView.OnPlaybackChangeListener;
import com.brightcove.mobile.mediaapi.model.Video;

public class VideoPlayer extends TiUIView {

	private int _low;
	private int _high;

	public VideoPlayer(TiViewProxy proxy) {
		super(proxy);
		_player = createPlayer();
		setNativeView(_player);
	}

	private BCPlayerView _player;

	private BCPlayerView createPlayer() {
		BCPlayerView player = new BCPlayerView(TiApplication.getAppCurrentActivity());
		player.setGravity(Gravity.CENTER);
		player.logEnabled(true);
		player.setOnErrorListener(new OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
				proxy.fireEvent("error", null);
				return false;
			}
		});
		player.setOnPlaybackChangeListener(new OnPlaybackChangeListener() {

			@Override
			public void onStop() {
				proxy.fireEvent("stop", null);
			}

			@Override
			public void onStart() {
				proxy.fireEvent("start", null);
			}
		});
		return player;
	}

	public BCPlayerView getPlayer() {
		return _player;
	}

	public void setVideo(Video video) {
		Util.i(video.getName() + " playing " + video.getFlvUrl());
		getPlayer().load(video);
		getPlayer().start();
	}

	public void setLowBitRate(double rate) {
		_low = (int) rate;
		if (_low > 0 && _high > 0)
			getPlayer().setRenditionBitRateRange(_low, _high);
	}

	public void setHighBitRate(double rate) {
		_high = (int) rate;
		if (_low > 0 && _high > 0)
			getPlayer().setRenditionBitRateRange(_low, _high);
	}

	public void start() {
		getPlayer().start();
	}

}
