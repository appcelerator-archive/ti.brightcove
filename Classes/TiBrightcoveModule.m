/**
 * Your Copyright Here
 *
 * Appcelerator Titanium is Copyright (c) 2009-2010 by Appcelerator, Inc.
 * and licensed under the Apache Public License (version 2)
 */
#import "TiBrightcoveModule.h"
#import "TiBase.h"
#import "TiHost.h"
#import "TiUtils.h"
#import "TiApp.h"
#import "BCMediaAPI.h"
#import "BCVideo.h"
#import "BCMoviePlayerController.h"

@implementation TiBrightcoveModule

#pragma mark Internal

// this is generated for your module, please do not change it
-(id)moduleGUID
{
	return @"2c94b236-9c45-4202-835b-85bb13b261ae";
}

// this is generated for your module, please do not change it
-(NSString*)moduleId
{
	return @"ti.brightcove";
}

-(void)test:(id)args
{
	BCMediaAPI *bc = [[BCMediaAPI alloc] initWithReadToken:@"YmvFO5RekMdbqKGyT5CnLWzOR-J4Cq2IwpBV08B6vuwiBAUrRYQkmA.."];
	NSError *error = nil;
	BCVideo *video = [bc findVideoById:95654379001 error:&error];
	if (error!=nil)
	{
		NSLog(@"[ERROR] %@",error);
	}
	else 
	{
		BCMoviePlayerController *controller = [[BCMoviePlayerController alloc]init];
		[controller setContentURL:video];
		controller.view.frame = CGRectMake(0, 0, 480.0, 320.0);
		UIViewController *root = [[TiApp app] controller];
		[root.view addSubview:controller.view];
	}

}


@end
