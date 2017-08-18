window.appurl = "http://localhost";

function login(name, pwd) {
  var form = new FormData();
  form.append("username", name);
  form.append("passwd", pwd);

  fetch(window.appurl + "/users/login", {
    method: "POST",
    mode: "cors",
    body: form
  })
    .then(function(resp) {
      return resp.json();
    })
    .then(function(r) {
      // alert(JSON.stringify(r));
      if (r.flag === true) {
        var jwt = r.obj;
        sessionStorage.setItem("JWT", jwt);
        localStorage.setItem("JWT", jwt);
        var arr = jwt.split(".");
        
        var info = b64DecodeUnicode(arr[1]);
        var jsonObj = JSON.parse(info);
        sessionStorage.setItem("roles", jsonObj.roles);
        sessionStorage.setItem("name", jsonObj.name);
        sessionStorage.setItem("exp", jsonObj.exp);

        window.location.href = "./index.html";
      } else if (r.flag === false) {
        if (r.message === undefined) {
          if (r.obj.type === "ERROR") {
            if (r.obj.messages.length !== 0) {
              var sb = "";
              for (var i = 0; i < r.obj.messages.length; i++) {
                sb += r.obj.messages[i] + ",";
              }
              alert("警告：" + sb);
            }
          }
        } else {
          alert("警告：" + r.message);
        }
      }
      // if ("success" === r) {
      //     sessionStorage.setItem("name", name);
      //     window.location.href = "./index.html";
      // }
    });
}

var register = function() {
  document.getElementById("submit").onclick = function() {
    var name = document.getElementById("name").value;
    var pwd = document.getElementById("pwd").value;
    login(name, pwd);
  };
};

function b64DecodeUnicode(str) {
    return decodeURIComponent(atob(str).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
}


window.onload = function() {
  register();
};
