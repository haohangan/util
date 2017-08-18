window.onload = function() {
    document.getElementById("username").innerText = sessionStorage.getItem("name");
}

function toHtml(url) {
    var frame = document.getElementById("frame"); //.src = url
    frame.src = url;
}