/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */

package ti.brightcove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.HashMap;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;

import com.brightcove.mobile.mediaapi.ReadAPI;
import com.brightcove.mobile.mediaapi.model.ItemCollection;
import com.brightcove.mobile.mediaapi.model.Playlist;
import com.brightcove.mobile.mediaapi.model.Video;
import com.brightcove.mobile.mediaapi.model.enums.MediaDeliveryTypeEnum;
import com.brightcove.mobile.mediaapi.model.enums.PlaylistFieldEnum;
import com.brightcove.mobile.mediaapi.model.enums.PlaylistTypeEnum;
import com.brightcove.mobile.mediaapi.model.enums.RegionEnum;
import com.brightcove.mobile.mediaapi.model.enums.SortByTypeEnum;
import com.brightcove.mobile.mediaapi.model.enums.SortOrderTypeEnum;
import com.brightcove.mobile.mediaapi.model.enums.VideoStateFilterEnum;
import com.brightcove.mobile.mediaapi.model.enums.VideoFieldEnum;

@Kroll.module(name="Brightcove", id="ti.brightcove")
public class BrightcoveModule extends KrollModule
{
	// Standard Debugging variables
	private static final String LCAT = "BrightcoveModule";
		
	public BrightcoveModule() {
		super();
	}

	//
	// Properties
	//
	@Kroll.setProperty
	public void setReadToken(String val) {
		Constants.setReadToken(val);
	}

	@Kroll.setProperty
	public void setDeliveryType(int val) {
		Constants.getAPI().setMediaDeliveryType(
				MediaDeliveryTypeEnum.values()[val]);
	}

	@Kroll.setProperty
	public void setRegion(int val) {
		Constants.getAPI().setRegion(RegionEnum.values()[val]);
	}

	@Kroll.setProperty
	public void setLogging(boolean val) {
		if (val) {
			Logger APILogger = Logger.getLogger("BCAndroidAPILogger");
			Constants.getAPI().setLogger(APILogger);
		} else {
			Constants.getAPI().setLogger(null);
		}
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
	// Internal Utility Methods / Classes
	//
	private void populateResponseFromVideoCollection(HashMap response,
			ItemCollection<Video> items) {
		response.put("pageNumber", items.getPageNumber());
		response.put("pageSize", items.getPageSize());
		response.put("totalCount", items.getTotalCount());
		ArrayList<VideoProxy> proxied = new ArrayList<VideoProxy>();
		for (Video item : items.getItems()) {
			proxied.add(new VideoProxy(item));
		}
		response.put("videos", proxied.toArray());
	}
	
	private void populateResponseFromPlaylistCollection(HashMap response,
			ItemCollection<Playlist> items) {
		response.put("pageNumber", items.getPageNumber());
		response.put("pageSize", items.getPageSize());
		response.put("totalCount", items.getTotalCount());
		ArrayList<PlaylistProxy> proxied = new ArrayList<PlaylistProxy>();
		for (Playlist item : items.getItems()) {
			proxied.add(new PlaylistProxy(item));
		}
		response.put("playlists", proxied.toArray());
	}

	private class FieldsResult {
		public EnumSet<VideoFieldEnum> videoFields;
		public HashSet<String> customFields;
		public EnumSet<PlaylistFieldEnum> playlistFields;

		public FieldsResult(HashMap args) {
		    KrollDict argsDict = new KrollDict(args);
			if (args.containsKey("videoFields")) {
				String[] rawVideoFields = argsDict.getStringArray("videoFields");
				videoFields = VideoFieldEnum.createEmptyEnumSet();
				for (String rawField : rawVideoFields) {
					videoFields.add(VideoFieldEnum.values()[Integer
							.parseInt(rawField)]);
				}
			}
			if (args.containsKey("customFields")) {
				KrollDict rawCustomFields = argsDict.getKrollDict("customFields");
				customFields = new HashSet<String>();
				for (String key : rawCustomFields.keySet()) {
					customFields.add(rawCustomFields.getString(key));
				}
			}

			if (args.containsKey("playlistFields")) {
				String[] rawPlaylistFields = argsDict
						.getStringArray("playlistFields");
				playlistFields = PlaylistFieldEnum.createEmptyEnumSet();
				for (String rawField : rawPlaylistFields) {
					playlistFields.add(PlaylistFieldEnum.values()[Integer
							.parseInt(rawField)]);
				}
			}
		}
	}
	
	//
	// Methods
	//
	@Kroll.method
	public HashMap getVideos(Object[] arguments) {
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			HashMap args = (arguments.length > 0 ? (HashMap) arguments[0] : new HashMap());
			KrollDict argsDict = new KrollDict(args);
			String pUserId = argsDict.getString("userId");
			if (pUserId == null)
				pUserId = argsDict.getString("userID");
			Integer pPageSize = argsDict.optInt("pageSize", 100);
			Integer pPageNumber = argsDict.optInt("pageNumber", 0);
			Boolean pGetItemCount = true;
			SortByTypeEnum pSortBy = SortByTypeEnum.values()[argsDict.optInt("sortType", 0)];
			SortOrderTypeEnum pSortOrderType = SortOrderTypeEnum.values()[argsDict.optInt("sortOrder", 0)];
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			Set<String> pCustomFields = fields.customFields;
			
			populateResponseFromVideoCollection(response,
					readAPI.findAllVideos(pPageSize, pPageNumber, pGetItemCount, pSortBy, pSortOrderType, pVideoFields, pCustomFields));
		} catch (Exception e) {
			Util.e("Failed to getVideos", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getVideosByIDs(HashMap args) {
		return getVideosByIds(args);
	}
	@Kroll.method
	public HashMap getVideosByIds(HashMap args) {
	    KrollDict argsDict = new KrollDict(args);
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			String[] rawIds = argsDict.getStringArray("ids");
			HashSet<Long> ids = new HashSet<Long>();
			for (String rawId : rawIds) {
				ids.add(Long.parseLong(rawId));
			}
			FieldsResult fields = new FieldsResult(args);

			populateResponseFromVideoCollection(response,
					readAPI.findVideosByIds(ids, fields.videoFields,
							fields.customFields));
		} catch (Exception e) {
			Util.e("Failed to getVideosByIds", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getRelatedVideos(HashMap args) {
	    KrollDict argsDict = new KrollDict(args);
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			Long pVideoId = (long) argsDict.getInt("id");
			String pReferenceId = argsDict.getString("referenceId");
			if (pReferenceId == null) {
				pReferenceId = argsDict.getString("referenceID");
			}
			Integer pPageSize = argsDict.optInt("pageSize", 100);
			Integer pPageNumber = argsDict.optInt("pageNumber", 0);
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			HashSet<String> pCustomFields = fields.customFields;

			populateResponseFromVideoCollection(
					response,
					readAPI.findRelatedVideos(pVideoId, pReferenceId,
							pPageSize, pPageNumber, pVideoFields, pCustomFields));
		} catch (Exception e) {
			Util.e("Failed to getRelatedVideos", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getVideosByReferenceIDs(HashMap args) {
		return getVideosByReferenceIds(args);
	}

	@Kroll.method
	public HashMap getVideosByReferenceIds(HashMap args) {
	    KrollDict argsDict = new KrollDict(args);
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			String[] rawReferenceIds = argsDict.getStringArray("referenceIds");
			if (rawReferenceIds == null) {
				rawReferenceIds = argsDict.getStringArray("referenceIDs");
			}
			HashSet<String> pReferenceIds = new HashSet<String>(
					Arrays.asList(rawReferenceIds));
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			HashSet<String> pCustomFields = fields.customFields;

			populateResponseFromVideoCollection(response,
					readAPI.findVideosByReferenceIds(pReferenceIds,
							pVideoFields, pCustomFields));
		} catch (Exception e) {
			Util.e("Failed to getVideosByReferenceIds", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getModifiedVideos(HashMap args) {
	    KrollDict argsDict = new KrollDict(args);
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {

			Date pFromDate = (Date) argsDict.get("date");
			Set<VideoStateFilterEnum> pFilter = null;
			String[] rawFilters = argsDict.getStringArray("filters");
			if (rawFilters != null) {
				pFilter = VideoStateFilterEnum.createEmptySet();
				for (String rawFilter : rawFilters) {
					pFilter.add(VideoStateFilterEnum.values()[Integer
							.parseInt(rawFilter)]);
				}
			}
			Integer pPageSize = argsDict.optInt("pageSize", 100);
			Integer pPageNumber = argsDict.optInt("pageNumber", 0);
			SortByTypeEnum pSortBy = SortByTypeEnum.values()[argsDict.optInt("sortType", 0)];
			SortOrderTypeEnum pSortOrderType = SortOrderTypeEnum.values()[argsDict.optInt("sortOrder", 0)];
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			Set<String> pCustomFields = fields.customFields;
			
			populateResponseFromVideoCollection(response,
					readAPI.findModifiedVideos(pFromDate, pFilter, pPageSize, pPageNumber, pSortBy, pSortOrderType, pVideoFields, pCustomFields));
		} catch (Exception e) {
			Util.e("Failed to getModifiedVideos", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getVideosByText(HashMap args) {
		Log.w(LCAT, "getVideosByText has been deprecated.");
	    KrollDict argsDict = new KrollDict(args);
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			String pText = argsDict.getString("text");
			Integer pPageSize = argsDict.optInt("pageSize", 100);
			Integer pPageNumber = argsDict.optInt("pageNumber", 0);
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			Set<String> pCustomFields = fields.customFields;
			
			populateResponseFromVideoCollection(response,
					readAPI.findVideosByText(pText, pPageSize, pPageNumber, pVideoFields, pCustomFields));
		} catch (Exception e) {
			Util.e("Failed to getVideosByText", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getVideosByTags(HashMap args) {
		Log.w(LCAT, "getVideosByTags has been deprecated.");
	    KrollDict argsDict = new KrollDict(args);
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			String[] rawAndTags = argsDict.getStringArray("andTags");
			HashSet<String> pAndTags = new HashSet<String>(
					Arrays.asList(rawAndTags));
			String[] rawOrTags = argsDict.getStringArray("orTags");
			HashSet<String> pOrTags = new HashSet<String>(
					Arrays.asList(rawOrTags));
			Integer pPageSize = argsDict.optInt("pageSize", 100);
			Integer pPageNumber = argsDict.optInt("pageNumber", 0);
			SortByTypeEnum pSortBy = SortByTypeEnum.values()[argsDict.optInt("sortType", 0)];
			SortOrderTypeEnum pSortOrderType = SortOrderTypeEnum.values()[argsDict.optInt("sortOrder", 0)];
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			Set<String> pCustomFields = fields.customFields;

			populateResponseFromVideoCollection(response,
					readAPI.findVideosByTags(pAndTags, pOrTags, pPageSize,
							pPageNumber, pSortBy, pSortOrderType, pVideoFields,
							pCustomFields));
		} catch (Exception e) {
			Util.e("Failed to getVideosByTags", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getPlaylists(Object[] arguments) {
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			HashMap args = (arguments.length > 0 ? (HashMap) arguments[0] : new HashMap());
			KrollDict argsDict = new KrollDict(args);
			Integer pPageSize = argsDict.optInt("pageSize", 100);
			Integer pPageNumber = argsDict.optInt("pageNumber", 0);
			Boolean pGetItemCount = true;
			SortByTypeEnum pSortBy = SortByTypeEnum.values()[argsDict.optInt("sortType", 0)];
			SortOrderTypeEnum pSortOrderType = SortOrderTypeEnum.values()[argsDict.optInt("sortOrder", 0)];
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			Set<String> pCustomFields = fields.customFields;
			EnumSet<PlaylistFieldEnum> pPlaylistFields = fields.playlistFields;
			populateResponseFromPlaylistCollection(response,
					readAPI.findAllPlaylists(pPageSize, pPageNumber,
							pGetItemCount, pSortBy, pSortOrderType,
							pVideoFields, pCustomFields, pPlaylistFields));
		} catch (Exception e) {
			Util.e("Failed to getPlaylists", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getPlaylistsByIds(HashMap args) {
	    KrollDict argsDict = new KrollDict(args);
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			HashSet<Long> pPlaylistIds = new HashSet<Long>();
			for (String rawId : argsDict.getStringArray("ids")) {
				pPlaylistIds.add(Long.parseLong(rawId));
			}
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			Set<String> pCustomFields = fields.customFields;
			EnumSet<PlaylistFieldEnum> pPlaylistFields = fields.playlistFields;
			populateResponseFromPlaylistCollection(response,
					readAPI.findPlaylistsByIds(pPlaylistIds, pVideoFields, pCustomFields, pPlaylistFields));
		} catch (Exception e) {
			Util.e("Failed to getPlaylistsByIds", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getPlaylistsByReferenceIds(HashMap args) {
	    KrollDict argsDict = new KrollDict(args);
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			HashSet<String> pReferenceIds = new HashSet<String>(
					Arrays.asList(argsDict.getStringArray("referenceIds")));
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			Set<String> pCustomFields = fields.customFields;
			EnumSet<PlaylistFieldEnum> pPlaylistFields = fields.playlistFields;
			populateResponseFromPlaylistCollection(response,
					readAPI.findPlaylistsByReferenceIds(pReferenceIds,
							pVideoFields, pCustomFields, pPlaylistFields));
		} catch (Exception e) {
			Util.e("Failed to getPlaylistsByReferenceIds", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public HashMap getPlaylistsByPlayerId(HashMap args) {
	    KrollDict argsDict = new KrollDict(args);
		ReadAPI readAPI = Constants.getAPI();
		HashMap response = new HashMap();
		try {
			Long pPlayerId = (long)argsDict.getInt(args.containsKey("playerId") ? "playerId" : "playerID");
			Integer pPageSize = argsDict.optInt("pageSize", 100);
			Integer pPageNumber = argsDict.optInt("pageNumber", 0);
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			Set<String> pCustomFields = fields.customFields;
			EnumSet<PlaylistFieldEnum> pPlaylistFields = fields.playlistFields;
			populateResponseFromPlaylistCollection(response,
					readAPI.findPlaylistsForPlayerId(pPlayerId, pPageSize, pPageNumber, pVideoFields, pCustomFields, pPlaylistFields));
		} catch (Exception e) {
			Util.e("Failed to getPlaylistsByPlayerId", e);
			response.put("error", e.toString());
		}
		return response;
	}
}
