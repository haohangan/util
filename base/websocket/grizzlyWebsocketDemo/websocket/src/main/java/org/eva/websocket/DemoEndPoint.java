package org.eva.websocket;

import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/hello")
public class DemoEndPoint {
	 private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@OnOpen
	public void onOpen(Session session,EndpointConfig endpoint){
		logger.info("Connected ... " + session.getId());
	}
	
	@OnMessage
	public String onMessage(String message, Session session) {
		System.out.println("Greeting received:" + message);
		return "ok:"+message;
	}
	
	@OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }

}
