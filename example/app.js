// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// open a single window
var window = Ti.UI.createWindow({
  backgroundColor:'white'
});

Ti.Brightcove = require('ti.brightcove');
Ti.Brightcove.readToken = "<<<YOUR READ TOKEN HERE>>>";

var vButton = Ti.UI.createButton({
	title:'Get videos',
	width:200,
	height:40,
	top:20
});
vButton.addEventListener('click', function() {
	var videoInfo = Ti.Brightcove.getVideos();
	var vidWin = Ti.UI.createWindow({
		url:'videos.js',
		videos:videoInfo.videos,
	});
	vidWin.open();
});
window.add(vButton);

var pButton = Ti.UI.createButton({
	title:'Get playlists',
	width:200,
	height:40,
	top:80
});
pButton.addEventListener('click', function() {
	var playlistInfo = Ti.Brightcove.getPlaylists();
	var listWin = Ti.UI.createWindow({
		url:'playlists.js',
		playlistInfo:playlistInfo,
	});
	listWin.open();
});
window.add(pButton);

window.open();