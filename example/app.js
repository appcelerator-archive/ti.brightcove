var Brightcove = require('ti.brightcove');
Brightcove.readToken = '<<<YOUR READ TOKEN HERE>>>';
Brightcove.logging = true;

var win = Ti.UI.createWindow({
    backgroundColor: 'white'
});

var vButton = Ti.UI.createButton({
    title: 'Get videos',
    width: 200, height: 40,
    top: 20
});
vButton.addEventListener('click', function() {
    var result = Brightcove.getVideos();
    if (result.error) {
        alert(result.error);
    }
    else {
        Ti.UI.createWindow({
            backgroundColor: 'white',
            url: 'videos.js',
            videos: result.videos
        }).open();
    }
});
win.add(vButton);

var pButton = Ti.UI.createButton({
    title: 'Get playlists',
    width: 200, height: 40,
    top: 80
});
pButton.addEventListener('click', function() {
    var result = Brightcove.getPlaylists();
    if (result.error) {
        alert(result.error);
    }
    else {
        Ti.UI.createWindow({
            backgroundColor: 'white',
            url: 'playlists.js',
            playlistInfo: result
        }).open();
    }
});
win.add(pButton);

win.open();