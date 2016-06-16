package com.grgbanking.aptoto.cometd;

import java.util.Map;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;

/**
 * @author GuiHao <br>
 *         ghao3@grgbanking.com <br>
 *         2016年6月14日 下午3:47:49 <br>
 *         监听信息到达的事件
 */
public class ChatListener implements ClientSessionChannel.MessageListener {

	@Override
	public void onMessage(ClientSessionChannel channel, Message message) {
		Map<String, Object> data = message.getDataAsMap();
		String fromUser = (String) data.get("user");
		String text = (String) data.get("content");
		System.err.printf("%s: %s%n", fromUser, text);
	}

}
