/**
 * 创建websocket连接
 */
var host = "localhost";
var port = 8888;
var basePath = "/websocket";
var websocket;
var status = -1;

function conn(){
	websocket = new WebSocket("ws://127.0.0.1/websocket?uname=tom","zookeeperWS");
	console.info('开始连接');
	websocket.onopen = function(){
		console.info("打开websocket.");
		status = 1;
	};
	websocket.onclose = function(){
		console.info("关闭websocket.");
	};
	websocket.onerror =  function(){
		console.info("websocket error");
	};
	websocket.onmessage = function(event){
		console.info("websocket message:"+event.data);
	};
}

function send(message){
	websocket.send(message);
}

function sendBin(message){
	var binary = new Uint8Array(message.length);
	for (var i = 0; i < message.length; i++) {
	binary[i] = message.charCodeAt(i);
    }
	console.info(binary.byteLength);
	websocket.send(binary.buffer);
}

function closeWebsocket(){
    websocket.close();
}

function b64EncodeUnicode(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function(match, p1) {
        return String.fromCharCode('0x' + p1);
    }));
}

function b64DecodeUnicode(str) {
    return decodeURIComponent(Array.prototype.map.call(atob(str), function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
}