package com.grgbanking.aptoto.cometd;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.cometd.bayeux.Channel;
import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.LongPollingTransport;
import org.eclipse.jetty.client.HttpClient;

public class Demo {

	private volatile BayeuxClient client;
	private final ChatListener chatListener = new ChatListener();
//	private final MembersListener membersListener = new MembersListener();

	final static String defaultURL = "http://localhost:8483/cometd/cometd";
	final static String SERVICE = "/service/chat";

	public static void main(String[] args) throws Exception {
		Demo d = new Demo();
		d.run();
	}

	private void run() throws Exception {
		String url = defaultURL;

		HttpClient httpClient = new HttpClient();
		httpClient.start();

		client = new BayeuxClient(url, new LongPollingTransport(null, httpClient));
		client.getChannel(Channel.META_HANDSHAKE).addListener(new InitializerListener());
		client.getChannel(Channel.META_CONNECT).addListener(new ConnectionListener());

		client.handshake();
		boolean success = client.waitFor(1000, BayeuxClient.State.CONNECTED);
		if (!success) {
			System.err.printf("Could not handshake with server at %s%n", url);
			return;
		}

		ScheduledExecutorService pool  = Executors.newSingleThreadScheduledExecutor();
		pool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				String text = "qweqweq"+Math.random();
				Map<String, Object> data = new HashMap<>();
				data.put("user", "");
				data.put("tid", "0002");
				data.put("content", text);
				client.getChannel(SERVICE).publish(data);
				
			}
		}, 3, 3, TimeUnit.SECONDS);
//		client.disconnect(1000);

		// Map<String, Object> data = new HashMap<>();
		// data.put("user", nickname);
		// data.put("chat", text);
		// client.getChannel("/chat/demo").publish(data);
	}

	class InitializerListener implements ClientSessionChannel.MessageListener {

		@Override
		public void onMessage(ClientSessionChannel channel, Message message) {
			initialize();
		}

	}

	class ConnectionListener implements ClientSessionChannel.MessageListener {
		private boolean wasConnected;
		private boolean connected;

		@Override
		public void onMessage(ClientSessionChannel channel, Message message) {
			if (client.isDisconnected()) {
				connected = false;
				connectionClosed();
				System.out.println("关闭连接1");
				return;
			}

			wasConnected = connected;
			connected = message.isSuccessful();
			if (!wasConnected && connected) {
				connectionEstablished();
				System.out.println("不用管");
			} else if (wasConnected && !connected) {
				connectionBroken();
				System.out.println("关闭连接3");
			}
		}

	}

	private void initialize() {
		client.batch(new Runnable() {

			@Override
			public void run() {
				ClientSessionChannel chatChannel = client.getChannel(SERVICE);
				chatChannel.subscribe(chatListener);

				/*
				 * ClientSessionChannel membersChannel =
				 * client.getChannel("/members/demo");
				 * membersChannel.subscribe(membersListener);
				 */

				// Map<String, Object> data = new HashMap<>();
				// data.put("user", nickname);
				// data.put("membership", "join");
				// data.put("chat", nickname + " has joined");
				// chatChannel.publish(data);
			}

		});
	}

	private void connectionEstablished() {
		System.err.printf("system: Connection to Server Opened%n");
//		Map<String, Object> data = new HashMap<>();
//		data.put("user", nickname);
		// data.put("room", "/chat/demo");
		// client.getChannel("/service/members").publish(data);
	}

	private void connectionClosed() {
		System.err.printf("system: Connection to Server Closed%n");
	}

	private void connectionBroken() {
		System.err.printf("system: Connection to Server Broken%n");
	}
}
