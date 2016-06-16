package com.grgbanking.aptoto.cometd;

import java.util.Arrays;
import java.util.List;

import org.cometd.bayeux.Message;
import org.cometd.bayeux.client.ClientSessionChannel;

/**
 * 
 * @author GuiHao <br>
 *         ghao3@grgbanking.com <br>
 *         2016年6月14日 下午3:49:04 <br>
 *
 */
public class MembersListener implements ClientSessionChannel.MessageListener {

	@Override
	public void onMessage(ClientSessionChannel channel, Message message) {
		Object data = message.getData();
		Object[] members = data instanceof List ? ((List<?>) data).toArray() : (Object[]) data;
		System.err.printf("Members: %s%n", Arrays.asList(members));

	}

}
