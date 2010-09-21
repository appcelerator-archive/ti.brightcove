var win = Ti.UI.currentWindow;
win.backgroundColor = 'white';

var playlistInfo = win.playlistInfo;
var playlistRows = [];

for (var i=0; i < playlistInfo.playlists.length; i++) {
	var playlist = playlistInfo.playlists[i];
	var view = Ti.UI.createView({
		left:0,
		top:0,
		height:'auto',
		width:'auto'
	});
	
	var image = Ti.UI.createImageView({
		image:playlist.thumbnailURL,
		left:0,
		top:0,
		width:120,
		height:90
	});
	view.add(image);
	
	var name = Ti.UI.createLabel({
		text:playlist.name,
		top:10,
		left:130,
		height:'auto',
		width:'auto'
	});
	view.add(name);
	
	var desc = Ti.UI.createLabel({
		text:playlist.shortDescription,
		bottom:10,
		left:130,
		height:'auto',
		width:'auto'
	});
	view.add(desc);
	
	var row = Ti.UI.createTableViewRow({
		height:'auto',
		width:'auto',
		videos:playlist.videos
	});
	row.add(view);
	playlistRows.push(row);
}

var table = Ti.UI.createTableView({data:playlistRows});
table.addEventListener('click', function(e) {
	var vidWin = Ti.UI.createWindow({
		url:'videos.js',
		videos:e.row.videos,
	});
	vidWin.open();
});
win.add(table);