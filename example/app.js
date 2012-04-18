var Brightcove = require('ti.brightcove');
Brightcove.readToken = '<<<YOUR READ TOKEN HERE>>>';
Brightcove.logging = true;
if (Ti.Platform.name == 'android') {
    Brightcove.deliveryType = Brightcove.DELIVERY_HTTP;
}

var forceLoad = [ Ti.Media.createVideoPlayer ];

var win = Ti.UI.createWindow({
    backgroundColor: 'white'
});

var vButton = Ti.UI.createButton({
    title: 'Get Videos',
    width: 200, height: 40,
    top: 20
});
vButton.addEventListener('click', function () {
    showLoading();
    var result = Brightcove.getVideos({
        pageSize: 10,
        sortType: Brightcove.SORT_BY_PLAYS_TOTAL
    });
    if (result.error) {
        alert(result.error);
        hideLoading();
    }
    else {
        openWindow({
            url: 'videos.js',
            videos: result.videos
        });
    }
});
win.add(vButton);

var pButton = Ti.UI.createButton({
    title: 'Get Playlists',
    width: 200, height: 40,
    top: 80
});
pButton.addEventListener('click', function () {
    showLoading();
    var result = Brightcove.getPlaylists();
    if (result.error) {
        alert(result.error);
        hideLoading();
    }
    else {
        openWindow({
            url: 'playlists.js',
            playlistInfo: result
        });
    }
});
win.add(pButton);

win.open();

var loadingView;
function showLoading() {
    loadingView = Ti.UI.createView({
        backgroundColor: 'black'
    });
    var loading = Ti.UI.createImageView({
        images: [
            'images/loading/00.png', 'images/loading/01.png', 'images/loading/02.png',
            'images/loading/03.png', 'images/loading/04.png', 'images/loading/05.png',
            'images/loading/06.png', 'images/loading/07.png', 'images/loading/08.png',
            'images/loading/09.png', 'images/loading/10.png', 'images/loading/11.png'
        ],
        width: 33, height: 33,
        opacity: 0.4
    });
    loading.start();
    loadingView.add(loading);
    win.add(loadingView);
}

function hideLoading() {
    win.remove(loadingView);
}

function openWindow(obj) {
    obj.backgroundColor = 'white';
    obj.navBarHidden = false;
    var newWin = Ti.UI.createWindow(obj);
    newWin.addEventListener('close', hideLoading);
    newWin.open();
}
