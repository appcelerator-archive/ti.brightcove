Ti.Brightcove = require('ti.brightcove');
Ti.Brightcove.readToken = '<<<YOUR READ TOKEN HERE>>>';
Ti.Brightcove.logging = true;

var win = Ti.UI.createWindow({
    backgroundColor: 'white'
});

var vButton = Ti.UI.createButton({
    title: 'Get videos',
    width: 200, height: 40,
    top: 20
});
vButton.addEventListener('click', function() {
    var result = Ti.Brightcove.getVideos();
    if (result.error) {
        alert(result.error);
    }
    else {
        Ti.UI.createWindow({
            backgroundColor: 'white',
            url: 'videos.js',
            videos: result.videos,
            navBarHidden: false
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
    var result = Ti.Brightcove.getPlaylists();
    if (result.error) {
        alert(result.error);
    }
    else {
        Ti.UI.createWindow({
            backgroundColor: 'white',
            url: 'playlists.js',
            playlistInfo: result,
            navBarHidden: false
        }).open();
    }
});
win.add(pButton);

win.open();