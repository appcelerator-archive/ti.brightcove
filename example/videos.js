// Have to reload Brightcove, because this is a new context...
Ti.Brightcove = require('ti.brightcove');
Ti.Brightcove.readToken = '<<<YOUR READ TOKEN HERE>>>';
Ti.Brightcove.logging = true;

var win = Ti.UI.currentWindow;

var videos = win.videos;
var videoRows = [];

for (var i = 0; i < videos.length; i++) {
    var video = videos[i];
    var row = Ti.UI.createTableViewRow({
        height: 'auto', width: 'auto',
        video: video
    });
    var view = Ti.UI.createView({
        left: 0, top: 0,
        height: 'auto', width: 'auto'
    });
    view.add(Ti.UI.createImageView({
        image: video.thumbnailURL,
        left: 0, top: 0,
        width: 120, height: 90
    }));
    view.add(Ti.UI.createLabel({
        text: video.name,
        top: 10, left: 130,
        height: 'auto', width: 'auto'
    }));
    view.add(Ti.UI.createLabel({
        text: video.shortDescription,
        bottom: 10, left: 130,
        height: 'auto', width: 'auto'
    }));
    row.add(view);
    videoRows.push(row);
}

var table = Ti.UI.createTableView({data: videoRows});
table.addEventListener('click', function(e) {
    var vidWin = Ti.UI.createWindow({
        backgroundColor: 'black',
        navBarHidden: false
    });
    var player = Ti.Brightcove.createVideoPlayer({
        video: e.row.video
    });
    player.addEventListener('complete', function() {
        vidWin.close();
    });

    if (parseFloat(Titanium.Platform.version) >= 3.2) {
        player.movieControlStyle = Titanium.Media.VIDEO_CONTROL_FULLSCREEN;
        if (Titanium.Platform.osname == 'ipad') {
            player.width = 400;
            player.height = 300;
        }
        vidWin.add(player);
    }
    vidWin.open();
});
win.add(table);