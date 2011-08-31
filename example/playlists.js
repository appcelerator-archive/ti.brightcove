var win = Ti.UI.currentWindow;

var playlistInfo = win.playlistInfo;
var playlistRows = [];

for (var i = 0; i < playlistInfo.playlists.length; i++) {
    var playlist = playlistInfo.playlists[i];
    var row = Ti.UI.createTableViewRow({
        height: 'auto', width: 'auto',
        videos: playlist.videos
    });
    var view = Ti.UI.createView({
        left: 0, top: 0,
        height: 'auto', width: 'auto'
    });
    view.add(Ti.UI.createImageView({
        image: playlist.thumbnailURL,
        left: 0, top: 0,
        width: 120, height: 90
    }));
    view.add(Ti.UI.createLabel({
        text: playlist.name,
        top: 10, left: 130,
        height: 'auto', width: 'auto'
    }));
    view.add(Ti.UI.createLabel({
        text: playlist.shortDescription,
        bottom: 10, left: 130,
        height: 'auto', width: 'auto'
    }));
    row.add(view);
    playlistRows.push(row);
}

var table = Ti.UI.createTableView({
    data: playlistRows
});
table.addEventListener('click', function(e) {
    Ti.UI.createWindow({
        backgroundColor: 'white',
        url: 'videos.js',
        videos: e.row.videos,
        navBarHidden: false
    }).open();
});
win.add(table);