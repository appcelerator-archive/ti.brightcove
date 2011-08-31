/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2011 by Appcelerator, Inc. All Rights Reserved.
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

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;

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
	
	public BrightcoveModule(TiContext tiContext) {
		super(tiContext);
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
	private void populateResponseFromVideoCollection(KrollDict response,
			ItemCollection<Video> items) {
		response.put("pageNumber", items.getPageNumber());
		response.put("pageSize", items.getPageSize());
		response.put("totalCount", items.getTotalCount());
		ArrayList<VideoProxy> proxied = new ArrayList<VideoProxy>();
		for (Video item : items.getItems()) {
			proxied.add(new VideoProxy(context, item));
		}
		response.put("videos", proxied.toArray());
	}
	
	private void populateResponseFromPlaylistCollection(KrollDict response,
			ItemCollection<Playlist> items) {
		response.put("pageNumber", items.getPageNumber());
		response.put("pageSize", items.getPageSize());
		response.put("totalCount", items.getTotalCount());
		ArrayList<PlaylistProxy> proxied = new ArrayList<PlaylistProxy>();
		for (Playlist item : items.getItems()) {
			proxied.add(new PlaylistProxy(context, item));
		}
		response.put("playlists", proxied.toArray());
	}

	private class FieldsResult {
		public EnumSet<VideoFieldEnum> videoFields;
		public HashSet<String> customFields;
		public EnumSet<PlaylistFieldEnum> playlistFields;

		public FieldsResult(KrollDict args) {
			if (args.containsKey("videoFields")) {
				String[] rawVideoFields = args.getStringArray("videoFields");
				videoFields = VideoFieldEnum.createEmptyEnumSet();
				for (String rawField : rawVideoFields) {
					videoFields.add(VideoFieldEnum.values()[Integer
							.parseInt(rawField)]);
				}
			}
			if (args.containsKey("customFields")) {
				KrollDict rawCustomFields = args.getKrollDict("customFields");
				customFields = new HashSet<String>();
				for (String key : rawCustomFields.keySet()) {
					customFields.add(rawCustomFields.getString(key));
				}
			}

			if (args.containsKey("playlistFields")) {
				String[] rawPlaylistFields = args
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
	public KrollDict getVideos(Object[] arguments) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			KrollDict args = (arguments.length > 0 ? (KrollDict) arguments[0] : new KrollDict());
			String pUserId = args.getString("userId");
			if (pUserId == null)
				pUserId = args.getString("userID");
			Integer pPageSize = args.optInt("pageSize", 100);
			Integer pPageNumber = args.optInt("pageNumber", 0);
			Boolean pGetItemCount = true;
			SortByTypeEnum pSortBy = SortByTypeEnum.values()[args.optInt("sortType", 0)];
			SortOrderTypeEnum pSortOrderType = SortOrderTypeEnum.values()[args.optInt("sortOrder", 0)];
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
	public KrollDict getVideosByIDs(KrollDict args) {
		return getVideosByIds(args);
	}
	@Kroll.method
	public KrollDict getVideosByIds(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			String[] rawIds = args.getStringArray("ids");
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
	public KrollDict getRelatedVideos(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			Long pVideoId = (long) args.getInt("id");
			String pReferenceId = args.getString("referenceId");
			if (pReferenceId == null) {
				pReferenceId = args.getString("referenceID");
			}
			Integer pPageSize = args.optInt("pageSize", 100);
			Integer pPageNumber = args.optInt("pageNumber", 0);
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
	public KrollDict getVideosByReferenceIDs(KrollDict args) {
		return getVideosByReferenceIds(args);
	}

	@Kroll.method
	public KrollDict getVideosByReferenceIds(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			String[] rawReferenceIds = args.getStringArray("referenceIds");
			if (rawReferenceIds == null) {
				rawReferenceIds = args.getStringArray("referenceIDs");
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
	public KrollDict getVideosByUserID(KrollDict args) {
		return getVideosByUserId(args);
	}
	@Kroll.method
	public KrollDict getVideosByUserId(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			String pUserId = args.getString("userId");
			if (pUserId == null)
				pUserId = args.getString("userID");
			Integer pPageSize = args.optInt("pageSize", 100);
			Integer pPageNumber = args.optInt("pageNumber", 0);
			SortByTypeEnum pSortBy = SortByTypeEnum.values()[args.optInt("sortType", 0)];
			SortOrderTypeEnum pSortOrderType = SortOrderTypeEnum.values()[args.optInt("sortOrder", 0)];
			FieldsResult fields = new FieldsResult(args);
			EnumSet<VideoFieldEnum> pVideoFields = fields.videoFields;
			Set<String> pCustomFields = fields.customFields;
			
			populateResponseFromVideoCollection(response,
					readAPI.findVideosByUserId(pUserId, pPageSize, pPageNumber, pSortBy, pSortOrderType, pVideoFields, pCustomFields));
		} catch (Exception e) {
			Util.e("Failed to getVideosByUserId", e);
			response.put("error", e.toString());
		}
		return response;
	}

	@Kroll.method
	public KrollDict getModifiedVideos(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {

			Date pFromDate = (Date) args.get("date");
			Set<VideoStateFilterEnum> pFilter = null;
			String[] rawFilters = args.getStringArray("filters");
			if (rawFilters != null) {
				pFilter = VideoStateFilterEnum.createEmptySet();
				for (String rawFilter : rawFilters) {
					pFilter.add(VideoStateFilterEnum.values()[Integer
							.parseInt(rawFilter)]);
				}
			}
			Integer pPageSize = args.optInt("pageSize", 100);
			Integer pPageNumber = args.optInt("pageNumber", 0);
			SortByTypeEnum pSortBy = SortByTypeEnum.values()[args.optInt("sortType", 0)];
			SortOrderTypeEnum pSortOrderType = SortOrderTypeEnum.values()[args.optInt("sortOrder", 0)];
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
	public KrollDict getVideosByText(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			String pText = args.getString("text");
			Integer pPageSize = args.optInt("pageSize", 100);
			Integer pPageNumber = args.optInt("pageNumber", 0);
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
	public KrollDict getVideosByTags(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			String[] rawAndTags = args.getStringArray("andTags");
			HashSet<String> pAndTags = new HashSet<String>(
					Arrays.asList(rawAndTags));
			String[] rawOrTags = args.getStringArray("orTags");
			HashSet<String> pOrTags = new HashSet<String>(
					Arrays.asList(rawOrTags));
			Integer pPageSize = args.optInt("pageSize", 100);
			Integer pPageNumber = args.optInt("pageNumber", 0);
			SortByTypeEnum pSortBy = SortByTypeEnum.values()[args.optInt("sortType", 0)];
			SortOrderTypeEnum pSortOrderType = SortOrderTypeEnum.values()[args.optInt("sortOrder", 0)];
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
	public KrollDict getPlaylists(Object[] arguments) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			KrollDict args = (arguments.length > 0 ? (KrollDict) arguments[0] : new KrollDict());
			Integer pPageSize = args.optInt("pageSize", 100);
			Integer pPageNumber = args.optInt("pageNumber", 0);
			Boolean pGetItemCount = true;
			SortByTypeEnum pSortBy = SortByTypeEnum.values()[args.optInt("sortType", 0)];
			SortOrderTypeEnum pSortOrderType = SortOrderTypeEnum.values()[args.optInt("sortOrder", 0)];
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
	public KrollDict getPlaylistsByIds(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			HashSet<Long> pPlaylistIds = new HashSet<Long>();
			for (String rawId : args.getStringArray("ids")) {
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
	public KrollDict getPlaylistsByReferenceIds(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			HashSet<String> pReferenceIds = new HashSet<String>(
					Arrays.asList(args.getStringArray("referenceIds")));
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
	public KrollDict getPlaylistsByPlayerId(KrollDict args) {
		ReadAPI readAPI = Constants.getAPI();
		KrollDict response = new KrollDict();
		try {
			Long pPlayerId = (long)args.getInt(args.containsKey("playerId") ? "playerId" : "playerID");
			Integer pPageSize = args.optInt("pageSize", 100);
			Integer pPageNumber = args.optInt("pageNumber", 0);
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
