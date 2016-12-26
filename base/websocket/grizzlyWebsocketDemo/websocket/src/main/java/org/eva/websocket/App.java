package org.eva.websocket;


import java.util.HashSet;
import java.util.Set;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.server.Server;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) throws DeploymentException {
		String host = "localhost";
		int port = 8888;
		Set<Class<?>> set = new HashSet<>();
		set.add(DemoEndPoint.class);
		
		Server server = new Server(host,port,"/websocket",null,set);
		server.start();
		BootHelp.waitInput();
		server.stop();
	}
}
