package zk.utils.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import zk.utils.vo.NodeInfo;

public class Demo {
	static final Logger LOG = Logger.getGlobal();
	static {
		LOG.setLevel(Level.INFO);
	}

	static final String HOST = "127.0.0.1";
	static final int PORT = 2181;

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zk = new ZooKeeper(HOST + ":" + PORT, 3000, new Watcher() {

			@Override
			public void process(WatchedEvent arg0) {
				LOG.info(arg0 + "\t" + arg0.getPath() + "\t" + arg0.getState() + "\t" + arg0.getType());
			}

		});
		// zk.close();
		LOG.info(zk.getState().toString());
		NodeInfo ni = new NodeInfo();
		ni.setName("app1");
		Map<String,String> map = new HashMap<>();
		map.put("C", "D");
		ni.setMap(map);
//		zk.setData("/", ni.toJson().getBytes(StandardCharsets.UTF_8), -1);
		zk.setData("/", "".getBytes(), -1);
		System.out.println("data:" + new String(zk.getData("/", false, null)));
		zk.close();
	}
}
