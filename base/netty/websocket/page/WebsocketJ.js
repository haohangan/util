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

function closeWebsocket(){
    websocket.close();
}