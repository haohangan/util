/**   
 * 文件名：ChatService.java </br>
 * 描述：</br>
 */

package grg.aptoto.im;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.server.AbstractService;
import org.cometd.server.ServerChannelImpl;

/**
 * 类名: ChatService </br>
 * 包名：grg.aptoto.im </br>
 * 描述: </br>
 * 发布版本号：</br>
 * 开发人员： 何锦荣 </br>
 * 创建时间： 2016-6-8 </br>
 */

public class ChatService extends AbstractService {

	/**
	 * 描述: </br>
	 * 开发人员：何锦荣 </br>
	 * 创建时间：2016-6-8 </br>
	 * 
	 * @param bayeux
	 * @param name
	 *            </br>
	 */
	public ChatService(BayeuxServer bayeux) {
		super(bayeux, "chat");
		addService("/service/chat", "processChat");
		addService("/service/privatechat", "privateChat");
	}

	/**
	 * 
	 * 方法名: </br>
	 * 详述: </br>
	 * 开发人员：何锦荣 </br>
	 * 创建时间：2016-6-8 </br>
	 * 
	 * @param remote
	 * @param message
	 *            </br>
	 */
	public void processChat(ServerSession remote, ServerMessage.Mutable message) {
		Map<String, Object> input = message.getDataAsMap();
		String name = (String) input.get("name");

		Map<String, Object> output = new HashMap<>();
		output.put("greeting", "Hello, " + name);
		System.out.println("name:" + name);
		// 将信息发送给订阅了“/channel/chat”通道的用户
		// remote.deliver(getServerSession(), "/channel/chat", output);
		ServerChannelImpl channel = (ServerChannelImpl) getBayeux().getChannel("/channel/chat");
		Set<ServerSession> subscribers = channel.getSubscribers();
		for (ServerSession subscriber : subscribers) {
			if (subscriber != remote) {
				subscriber.deliver(remote, "/channel/chat", output);
			}
		}
	}

	public void privateChat(ServerSession remote, ServerMessage message) {

	}

}
