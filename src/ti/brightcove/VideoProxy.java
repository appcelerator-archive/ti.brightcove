package ti.brightcove;

import java.util.Date;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;

@Kroll.proxy
public class VideoProxy extends KrollProxy {

	public VideoProxy(TiContext context) {
		super(context);
	}
	
	@Kroll.getProperty
	public String getName() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
	@Kroll.getProperty
	public String getShortDescription() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
	@Kroll.getProperty
	public String getLongDescription() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
	@Kroll.getProperty
	public String getVideoStillURL() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
	@Kroll.getProperty
	public String getThumbnailURL() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
	@Kroll.getProperty
	public Date getCreationDate() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
	@Kroll.getProperty
	public Date getPublishedDate() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
	@Kroll.getProperty
	public Date getLastModifiedDate() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.getProperty
	public String[] getTags() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.getProperty
	public int getVideoID() {
		return getVideoId();
	}
	@Kroll.getProperty
	public int getVideoId() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return 0;
	}

	@Kroll.getProperty
	public int getReferenceID() {
		return getReferenceId();
	}
	@Kroll.getProperty
	public int getReferenceId() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return 0;
	}

	@Kroll.getProperty
	public int getAccountID() {
		return getAccountId();
	}
	@Kroll.getProperty
	public int getAccountId() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return 0;
	}

	@Kroll.getProperty
	public int getLength() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return 0;
	}

	@Kroll.getProperty
	public int getPlaysTotal() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return 0;
	}

	@Kroll.getProperty
	public int getPlaysTrailingWeek() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return 0;
	}
	
	@Kroll.getProperty
	public int getItemState() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return 0;
	}

	@Kroll.getProperty
	public KrollDict getCustomFields() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
}
