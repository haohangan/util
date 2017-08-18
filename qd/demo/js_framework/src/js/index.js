window.onload = function() {
    document.getElementById("username").innerText = sessionStorage.getItem("name");
}

function toHtml(url) {
    var frame = document.getElementById("frame"); //.src = url
    var i;
    for (i in frame) {
        console.info(i + " " + frame[i]);
    }
}