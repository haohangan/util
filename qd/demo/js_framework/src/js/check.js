if (sessionStorage.length === 0 && localStorage.length === 0) {
  window.location.href = "./login.html";
}

if (localStorage["JWT"] === undefined) {
  window.location.href = "./login.html";
}
sessionStorage.setItem("JWT",localStorage.getItem("JWT"));
if (sessionStorage["JWT"] === undefined) {
  window.location.href = "./login.html";
}

function getJWT() {
  if (sessionStorage.length === 0) {
    return null;
  }
  if (
    sessionStorage["JWT"] === undefined &&
    localStorage["JWT"] === undefined
  ) {
    return null;
  }
  if (sessionStorage["JWT"] === undefined) {
    return localStorage["JWT"];
  }
  return sessionStorage["JWT"];
}
