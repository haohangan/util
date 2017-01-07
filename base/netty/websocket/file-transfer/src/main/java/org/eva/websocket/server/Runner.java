package org.eva.websocket.server;

public class Runner {
	public static void main(String[] args) {
		try {
			new WSServer().run(80);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
