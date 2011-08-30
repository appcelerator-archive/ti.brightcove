package ti.brightcove;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiContext;

@Kroll.proxy
public class PlaylistProxy extends KrollProxy {

	public PlaylistProxy(TiContext context) {
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
	public String getPlaylistTypeString() {
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
	public int getPlaylistID() {
		return getPlaylistId();
	}
	@Kroll.getProperty
	public int getPlaylistId() {
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
	public int getPlaylistType() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return 0;
	}

	@Kroll.getProperty
	public int[] getVideoIds() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

	@Kroll.getProperty
	public VideoProxy[] getVideos() {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}

}
