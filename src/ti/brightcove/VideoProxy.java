/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2011 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */
package ti.brightcove;

import java.util.Date;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;

import com.brightcove.mobile.mediaapi.model.CustomField;
import com.brightcove.mobile.mediaapi.model.Video;

@Kroll.proxy
public class VideoProxy extends KrollProxy {

	private Video _video;
	
	public VideoProxy(TiContext context, Video video) {
		super(context);
		_video = video;
	}
	
	public Video getVideo() {
		return _video;
	}
	
	@Kroll.getProperty
	public String getName() {
		return _video.getName();
	}
	
	@Kroll.getProperty
	public String getShortDescription() {
		return _video.getShortDescription();
	}
	
	@Kroll.getProperty
	public String getLongDescription() {
		return _video.getLongDescription();
	}
	
	@Kroll.getProperty
	public String getVideoStillURL() {
		return _video.getVideoStillUrl();
	}
	
	@Kroll.getProperty
	public String getThumbnailURL() {
		return _video.getThumbnailUrl();
	}
	
	@Kroll.getProperty
	public Date getCreationDate() {
		return _video.getCreationDate();
	}
	
	@Kroll.getProperty
	public Date getPublishedDate() {
		return _video.getPublishedDate();
	}
	
	@Kroll.getProperty
	public Date getLastModifiedDate() {
		return _video.getLastModifiedDate();
	}

	@Kroll.getProperty
	public Object[] getTags() {
		return _video.getTags().toArray();
	}

	@Kroll.getProperty
	public int getVideoID() {
		return getVideoId();
	}
	@Kroll.getProperty
	public int getVideoId() {
		return Util.degrade(_video.getId());
	}

	@Kroll.getProperty
	public String getReferenceID() {
		return getReferenceId();
	}
	@Kroll.getProperty
	public String getReferenceId() {
		return _video.getReferenceId();
	}

	@Kroll.getProperty
	public int getAccountID() {
		return getAccountId();
	}
	@Kroll.getProperty
	public int getAccountId() {
		return Util.degrade(_video.getAccountId());
	}

	@Kroll.getProperty
	public int getLength() {
		return Util.degrade(_video.getLength());
	}

	@Kroll.getProperty
	public int getPlaysTotal() {
		return _video.getPlaysTotal();
	}

	@Kroll.getProperty
	public int getPlaysTrailingWeek() {
		return _video.getPlaysTrailingWeek();
	}
	
	@Kroll.getProperty
	public int getItemState() {
		return _video.getItemState().ordinal();
	}

	@Kroll.getProperty
	public KrollDict getCustomFields() {
		KrollDict retVal = new KrollDict();
		for (CustomField field : _video.getCustomFields()) {
			retVal.put(field.getName(), field.getValue());
		}
		return retVal;
	}
	
}
