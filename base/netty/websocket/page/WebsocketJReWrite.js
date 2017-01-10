/**
 * rewrite the js  
 */
function SocketJS(){
	var websocket;
	
	this.conn = function(url){
		websocket = new WebSocket(url,"zookeeperWS");
		websocket.onclose = function(){
			console.info("close websocket.");
		};
		websocket.onerror =  function(){
			console.info("websocket error");
		};
		websocket.onmessage = function(event){
			console.info("websocket message:"+event.data);
		};	
	};
	
	this.send = function(message){
		websocket.send(message);
	};

	this.sendBin = function(message){
		var text = b64EncodeUnicode(message);
		var binary = new Uint8Array(text.length);
		for (var i = 0; i < text.length; i++) {
		  binary[i] = text.charAt(i);
		}
		websocket.send(binary.buffer);
	};

	this.closeWebsocket = function(){
		websocket.close();
	};
	
}

function base64tobin(base64) {
  var text = window.atob(base64);
  var buffer = new ArrayBuffer(text.length);
  var view = new DataView(buffer);
  for(var i=0, n=text.length; i<n; i++) view.setUint8(i,text.charCodeAt(i));
  return buffer;
}

function b64EncodeUnicode(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function(match, p1) {
        return String.fromCharCode('0x' + p1);
    }));
}