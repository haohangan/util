package grg.aptoto.im.cometd;

import java.util.Map;
import java.util.Set;

import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;

public class Listener implements ServerSession.MessageListener {

	@Override
	public boolean onMessage(ServerSession session, ServerSession sender, ServerMessage message) {
		System.out.println("进入listener==========");
		System.out.println("session:"+session);
		System.out.println("sender:"+sender);
		Set<Map.Entry<String,Object>> set =  message.entrySet();
		for(Map.Entry<String,Object> entry:set){
			System.out.print("\t"+entry.getKey()+":"+entry.getValue()+"\t");
		}
		System.out.println("离开listener");
		return false;
	}


}
