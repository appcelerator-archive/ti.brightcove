package ti.brightcove;

import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.kroll.annotations.Kroll;

import android.app.Activity;

@Kroll.proxy(creatableInModule = BrightcoveModule.class)
public class VideoPlayerProxy extends TiViewProxy {

	public VideoPlayerProxy(TiContext tiContext) {
		super(tiContext);
	}

	@Override
	public TiUIView createView(Activity arg0) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
		return null;
	}
	
	@Kroll.setProperty
	public void setVideo(VideoProxy video) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
	}

	@Kroll.setProperty
	public void setLowBitRate(double rate) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
	}

	@Kroll.setProperty
	public void setHighBitRate(double rate) {
		// TODO: implement this!
		Util.e("Not implemented yet!");
	}

}
