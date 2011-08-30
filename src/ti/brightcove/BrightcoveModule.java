/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2011 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */
package ti.brightcove;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiContext;

import com.brightcove.mobile.mediaapi.model.enums.MediaDeliveryTypeEnum;
import com.brightcove.mobile.mediaapi.model.enums.PlaylistTypeEnum;
import com.brightcove.mobile.mediaapi.model.enums.RegionEnum;
import com.brightcove.mobile.mediaapi.model.enums.SortByTypeEnum;
import com.brightcove.mobile.mediaapi.model.enums.SortOrderTypeEnum;
import com.brightcove.mobile.mediaapi.model.enums.VideoStateFilterEnum;

@Kroll.module(name="Brightcove", id="ti.brightcove")
public class BrightcoveModule extends KrollModule
{
	public BrightcoveModule(TiContext tiContext) {
		super(tiContext);
	}
	
	//
	// Properties
	//
	@Kroll.setProperty
	public void setReadToken(String val) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
	}
	
	@Kroll.setProperty
	public void setDeliveryType(int val) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
	}
	
	@Kroll.setProperty
	public void setRegion(int val) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
	}
	
	@Kroll.setProperty
	public void setLogging(boolean val) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
	}
	
	//
	// Constants
	//
	@Kroll.constant public static final int SORT_BY_PUBLISH_DATE = SortByTypeEnum.PUBLISH_DATE.ordinal();
	@Kroll.constant public static final int SORT_BY_CREATION_DATE = SortByTypeEnum.CREATION_DATE.ordinal();
	@Kroll.constant public static final int SORT_BY_MODIFIED_DATE = SortByTypeEnum.MODIFIED_DATE.ordinal();
	@Kroll.constant public static final int SORT_BY_PLAYS_TOTAL = SortByTypeEnum.PLAYS_TOTAL.ordinal();
	@Kroll.constant public static final int SORT_BY_PLAYS_TRAILING_WEEK = SortByTypeEnum.PLAYS_TRAILING_WEEK.ordinal();
	@Kroll.constant public static final int ORDER_ASCENDING = SortOrderTypeEnum.ASC.ordinal();
	@Kroll.constant public static final int ORDER_DESCENDING = SortOrderTypeEnum.DESC.ordinal();
	@Kroll.constant public static final int STATE_ACTIVE = VideoStateFilterEnum.PLAYABLE.ordinal();
	@Kroll.constant public static final int STATE_INACTIVE = VideoStateFilterEnum.INACTIVE.ordinal();
	@Kroll.constant public static final int STATE_DELETED = VideoStateFilterEnum.DELETED.ordinal();
	@Kroll.constant public static final int PLAYLIST_OLDEST_TO_NEWEST = PlaylistTypeEnum.OLDEST_TO_NEWEST.ordinal();
	@Kroll.constant public static final int PLAYLIST_NEWEST_TO_OLDEST = PlaylistTypeEnum.NEWEST_TO_OLDEST.ordinal();
	@Kroll.constant public static final int PLAYLIST_START_OLDEST_TO_NEWEST = PlaylistTypeEnum.OLDEST_TO_NEWEST.ordinal();
	@Kroll.constant public static final int PLAYLIST_START_NEWEST_TO_OLDEST = PlaylistTypeEnum.NEWEST_TO_OLDEST.ordinal();
	@Kroll.constant public static final int PLAYLIST_ALPHABETICAL = PlaylistTypeEnum.ALPHABETICAL.ordinal();
	@Kroll.constant public static final int PLAYLIST_PLAYS_TOTAL = PlaylistTypeEnum.PLAYSTOTAL.ordinal();
	@Kroll.constant public static final int PLAYLIST_PLAYS_TRAILING_WEEK = PlaylistTypeEnum.PLAYS_TRAILING_WEEK.ordinal();
	@Kroll.constant public static final int PLAYLIST_EXPLICIT = PlaylistTypeEnum.EXPLICIT.ordinal();
	@Kroll.constant public static final int REGION_US = RegionEnum.US.ordinal();
	@Kroll.constant public static final int REGION_JP = RegionEnum.JP.ordinal();
	@Kroll.constant public static final int DELIVERY_DEFAULT = MediaDeliveryTypeEnum.DEFAULT.ordinal();
	@Kroll.constant public static final int DELIVERY_HTTP = MediaDeliveryTypeEnum.HTTP.ordinal();

	//
	// Methods
	//
	@Kroll.method
	public Object[] getVideos(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getVideosByIDs(KrollDict args) {
		return getVideosByIds(args);
	}
	@Kroll.method
	public Object[] getVideosByIds(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getRelatedVideos(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getVideosByReferenceIDs(KrollDict args) {
		return getVideosByIds(args);
	}
	@Kroll.method
	public Object[] getVideosByReferenceIds(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getVideosByUserID(KrollDict args) {
		return getVideosByUserId(args);
	}
	@Kroll.method
	public Object[] getVideosByUserId(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getModifiedVideos(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getVideosByText(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getVideosByTags(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getPlaylists(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getPlaylistsByIds(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getPlaylistsByReferenceIds(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.method
	public Object[] getPlaylistsByPlayerId(KrollDict args) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
}
