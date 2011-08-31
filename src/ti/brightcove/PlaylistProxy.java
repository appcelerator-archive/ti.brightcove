/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2011 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */
package ti.brightcove;

import java.util.List;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;

import com.brightcove.mobile.mediaapi.model.Playlist;
import com.brightcove.mobile.mediaapi.model.Video;

@Kroll.proxy
public class PlaylistProxy extends KrollProxy {

	private Playlist _playlist;
	
	public PlaylistProxy(TiContext context, Playlist playlist) {
		super(context);
		_playlist = playlist;
	}
	
	@Kroll.getProperty
	public String getName() {
		return _playlist.getName();
	}
	
	@Kroll.getProperty
	public String getShortDescription() {
		return _playlist.getShortDescription();
	}
	
	@Kroll.getProperty
	public String getPlaylistTypeString() {
		return _playlist.getPlaylistType().toString();
	}
	
	@Kroll.getProperty
	public int getPlaylistType() {
		return _playlist.getPlaylistType().ordinal();
	}
	
	@Kroll.getProperty
	public String getThumbnailURL() {
		return _playlist.getThumbnailUrl();
	}

	@Kroll.getProperty
	public int getPlaylistID() {
		return getPlaylistId();
	}
	@Kroll.getProperty
	public int getPlaylistId() {
		return Util.degrade(_playlist.getId());
	}

	@Kroll.getProperty
	public String getReferenceID() {
		return getReferenceId();
	}
	@Kroll.getProperty
	public String getReferenceId() {
		return _playlist.getReferenceId();
	}

	@Kroll.getProperty
	public int getAccountID() {
		return getAccountId();
	}
	@Kroll.getProperty
	public int getAccountId() {
		return Util.degrade(_playlist.getAccountId());
	}

	@Kroll.getProperty
	public int[] getVideoIds() {
		List<Long> ids = _playlist.getVideoIds();
		int[] retVal = new int[ids.size()];
		int key = 0;
		for (Long id : ids) {
			retVal[key++] = Util.degrade(id);
		}
		return retVal;
	}

	@Kroll.getProperty
	public VideoProxy[] getVideos() {
		List<Video> videos = _playlist.getVideos();
		VideoProxy[] retVal = new VideoProxy[videos.size()];
		int key = 0;
		for (Video video : videos) {
			retVal[key++] = new VideoProxy(context, video);
		}
		return retVal;
	}

}
