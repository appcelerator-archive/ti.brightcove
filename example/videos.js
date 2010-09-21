
// Have to reload Brightcove, because this is a new context...
Ti.Brightcove = require('ti.brightcove');
Ti.Brightcove.readToken = "YmvFO5RekMdbqKGyT5CnLWzOR-J4Cq2IwpBV08B6vuwiBAUrRYQkmA..";
Ti.Brightcove.logging = true;

var win = Ti.UI.currentWindow;
win.backgroundColor = 'white';

var videos = win.videos;
var videoRows = [];

for (var i=0; i < videos.length; i++) {
	var video = videos[i];
	var view = Ti.UI.createView({
		left:0,
		top:0,
		height:'auto',
		width:'auto'
	});
	
	var image = Ti.UI.createImageView({
		image:video.thumbnailURL,
		left:0,
		top:0,
		width:120,
		height:90
	});
	view.add(image);
	
	var name = Ti.UI.createLabel({
		text:video.name,
		top:10,
		left:130,
		height:'auto',
		width:'auto'
	});
	view.add(name);
	
	var desc = Ti.UI.createLabel({
		text:video.shortDescription,
		bottom:10,
		left:130,
		height:'auto',
		width:'auto'
	});
	view.add(desc);
	
	var row = Ti.UI.createTableViewRow({
		height:'auto',
		width:'auto',
		video:video
	});
	row.add(view);
	videoRows.push(row);
}

var table = Ti.UI.createTableView({data:videoRows});
table.addEventListener('click', function(e) {
	var vidWin = Ti.UI.createWindow({
		backgroundColor:'black'
	});
	var player = Ti.Brightcove.createVideoPlayer({
		video:e.row.video,
	});
	player.addEventListener('complete', function() {
		Ti.API.log('Completed!');
		vidWin.close();
	});
	
	if (parseFloat(Titanium.Platform.version) >= 3.2)
	{
		player.movieControlStyle = Titanium.Media.VIDEO_CONTROL_FULLSCREEN;
		if (Titanium.Platform.osname == "ipad") {
			player.width = 400;
			player.height = 300;
		}
		vidWin.add(player);
	}
	vidWin.open();
});
win.add(table);