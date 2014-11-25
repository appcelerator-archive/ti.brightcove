/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2013 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */

package ti.brightcove;

import org.appcelerator.kroll.common.TiConfig;

import com.brightcove.mobile.mediaapi.ReadAPI;
import com.brightcove.mobile.mediaapi.model.enums.MediaDeliveryTypeEnum;

public class Constants {
	public static final String LCAT = "BrightcoveModule";
	public static final boolean DBG = TiConfig.LOGD;

	private static String _readToken;

	public static void setReadToken(String value) {
		_readToken = value;
		if (value != null) {
			_readAPI = new ReadAPI(_readToken);
		} else {
			_readAPI = null;
		}
	}

	private static ReadAPI _readAPI;

	public static ReadAPI getAPI() {
		if (_readToken == null) {
			Util.e("READTOKEN NOT SET! BRIGHTCOVE WILL NOT BE ABLE TO FUNCTION PROPERLY!");
			return null;
		}
		if (_readAPI == null) {
			_readAPI = new ReadAPI(_readToken);
			_readAPI.setMediaDeliveryType(MediaDeliveryTypeEnum.HTTP);
		}
		return _readAPI;
	}
}