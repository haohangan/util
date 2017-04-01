require('./TxtMsg_pb');
var $ = require('jquery');
/**
 * 创建websocket连接
 */
var host = "localhost";
var port = 80;
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
		/*var i;
		for(i in message){
			console.info(i+"  :  "+message[i]);
		}*/
	};
}

function send(message){
	websocket.send(message);
}

function sendBin(userid,txt){
	var message = new proto.txt();
	message.setTId(userid);
	message.setTxt(txt);
	bytes = message.serializeBinary();
	websocket.send(bytes);
}

function closeWebsocket(){
    websocket.close();
}

   $("#Send_Button").click(function(){
	var text = $("#send").val();
	if(text === undefined || text === null){
		text = "nihao";
	}
	send(text);
	$("#send").val('');
   });
   
   $('#SendBin_Button').click(function(){
	   var tid = $("#tid").val();
		var txt = $("#txt").val();
		sendBin(tid,txt);
		$("#tid").val('');
		$("#txt").val('');
   });
   
   // function sendData(){
    // var text = document.getElementById("send").value;
	// if(text === undefined || text === null){
		// text = "nihao";
	// }
	// send(text);
	// document.getElementById("send").value = "";
   // }
   
   // function sendBinData(){
	// var tid = document.getElementById("tid").value;
	// var txt = document.getElementById("txt").value;
	// sendBin(tid,txt);
	// document.getElementById("tid").value = "";
	// document.getElementById("txt").value = "";
   // }
   
   $('#Open_Button').click(function(){
	   conn();
   });
   
   $('#Close_Button').click(function(){
	   alert('关闭');
       closeWebsocket();
   });
