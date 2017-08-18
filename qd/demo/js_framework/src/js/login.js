function login(name, pwd) {
    var form = new FormData();
    form.append('name', name);
    form.append('pwd', pwd);

    fetch("http://localhost:81/login", {
        method: "POST",
        mode: 'cors',
        body: form
    }).then(function(resp) {
        return resp.text();
    }).then(function(r) {
        if ("success" === r) {
            sessionStorage.setItem("name", name);
            window.location.href = "./index.html";
        }
    });
}

var register = function() {
    document.getElementById("submit").onclick = function() {
        var name = document.getElementById("name").value;
        var pwd = document.getElementById("pwd").value;
        login(name, pwd);
    }
}

window.onload = function() {
    register();
}