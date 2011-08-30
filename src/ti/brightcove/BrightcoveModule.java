/**
 * Ti.Brightcove Module
 * Copyright (c) 2010-2011 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */
package ti.brightcove;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiConfig;

@Kroll.module(name="Brightcove", id="ti.brightcove")
public class BrightcoveModule extends KrollModule
{
	public BrightcoveModule(TiContext tiContext) {
		super(tiContext);
	}
}
