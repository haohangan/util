if (sessionStorage.length == 0 || sessionStorage['name'] === undefined) {
    window.location.href = "./login.html";
}
console.info("name:" + sessionStorage.getItem("name"));