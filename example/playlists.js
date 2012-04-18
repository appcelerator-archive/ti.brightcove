var win = Ti.UI.currentWindow;

var playlistInfo = win.playlistInfo;
var playlistRows = [];

for (var i = 0; i < playlistInfo.playlists.length; i++) {
    var playlist = playlistInfo.playlists[i];
    var row = Ti.UI.createTableViewRow({
        height: 80,
        videos: playlist.videos
    });
    row.add(Ti.UI.createImageView({
        image: playlist.thumbnailURL,
        top: 0, left: 0,
        width: 120, height: 90
    }));
    row.add(Ti.UI.createLabel({
        text: playlist.name + '\n' + playlist.shortDescription,
        top: 10, right: 10, bottom: 10, left: 130
    }));
    playlistRows.push(row);
}

var table = Ti.UI.createTableView({
    data: playlistRows
});
table.addEventListener('click', function (e) {
    Ti.UI.createWindow({
        backgroundColor: 'white',
        url: 'videos.js',
        videos: e.row.videos,
        navBarHidden: false
    }).open();
});
win.add(table);