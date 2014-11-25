// Have to reload Brightcove, because this is a new context...
var Brightcove = require('ti.brightcove');
Brightcove.readToken = '<<<YOUR READ TOKEN HERE>>>';
Brightcove.logging = true;

var win = Ti.UI.currentWindow;

var videos = win.videos;
var videoRows = [];

for (var i = 0; i < videos.length; i++) {
    var video = videos[i];
    var row = Ti.UI.createTableViewRow({
        height: 80,
        video: video
    });
    row.add(Ti.UI.createImageView({
        image: video.thumbnailURL,
        top: 0, left: 0,
        width: 120, height: 90
    }));
    row.add(Ti.UI.createLabel({
        text: video.name + '\n' + video.shortDescription,
        top: 10, right: 10, bottom: 10, left: 130
    }));
    videoRows.push(row);
}

var table = Ti.UI.createTableView({
    data: videoRows
});
table.addEventListener('click', function (e) {
    var vidWin = Ti.UI.createWindow({
        backgroundColor: 'black',
        navBarHidden: false
    });
    var player = Brightcove.createVideoPlayer({
        video: e.row.video
    });
    player.addEventListener('complete', function () {
        vidWin.close();
    });

    if (Ti.Platform.name === 'iPhone OS') {
        if (parseFloat(Titanium.Platform.version) >= 3.2) {
            player.movieControlStyle = Titanium.Media.VIDEO_CONTROL_FULLSCREEN;
            vidWin.add(player);
        }
    }
    else {
        vidWin.add(player);
    }

    vidWin.open();
});
win.add(table);