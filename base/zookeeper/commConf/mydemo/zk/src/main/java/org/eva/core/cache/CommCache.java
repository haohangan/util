package org.eva.core.cache;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.eva.core.exception.ConfExcepion;
import org.eva.core.zk.CommZK;
import org.eva.core.zk.ZKManager;

import com.google.gson.Gson;

public class CommCache {
	public static final Charset CHARSET = StandardCharsets.UTF_8;
	public static final String IP = "ip";
	public static final String PORT = "port";
	static final ConcurrentHashMap<String, ConfNode> map = new ConcurrentHashMap<>();

	protected static void addNode(String name, ConfNode node) {
		map.put(name, node);
	}

	public static void removeNode(String name) {
		map.remove(name);
	}

	@SuppressWarnings("unchecked")
	public static String getUrl(String name, String urlKey) throws ConfExcepion{
		ConfNode node = map.get(name);
		if(node==null){
			ZKManager manager = CommZK.ZKMANAGER.getManager();
			try {
				Stat stat = manager.getStat(name);
				if(stat == null){
					throw new ConfExcepion("节点尚未加载，请先启动应用");
				}
				String data = new String(manager.getData(name));
				Gson gson = new Gson();
				node = new ConfNode(gson.fromJson(data, HashMap.class));
				CommCache.addNode(name, node);
				manager.watchPathLoop(name);
			} catch (KeeperException | InterruptedException e) {
				throw new ConfExcepion("zookeeper 错误："+e.getMessage());
			}
		}
		return node.loadUrl(urlKey);
	}
}
