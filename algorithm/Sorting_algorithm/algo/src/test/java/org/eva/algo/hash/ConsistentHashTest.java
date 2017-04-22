package org.eva.algo.hash;

import java.util.ArrayList;
import java.util.List;

import org.eva.algo.hash.consistent.ConsistentHash;
import org.eva.algo.hash.consistent.NettyServerNode;
import org.junit.Before;
import org.junit.Test;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class ConsistentHashTest {
	List<NettyServerNode> list;
	HashFunction hashFunction;

	@Before
	public void before() {
		list = new ArrayList<>();
		NettyServerNode server1 = new NettyServerNode.Builder().setNodeName("serverGuangzhou")
				.setUrl("ws://127.0.0.1:80/wesocket").build();
		list.add(server1);
		NettyServerNode server2 = new NettyServerNode.Builder().setNodeName("serverChangsha")
				.setUrl("ws://127.0.0.1:8080/wesocket").build();
		list.add(server2);
		NettyServerNode server3 = new NettyServerNode.Builder().setNodeName("serverXian")
				.setUrl("ws://127.0.0.1:8081/wesocket").build();
		list.add(server3);
		NettyServerNode server4 = new NettyServerNode.Builder().setNodeName("serverChangchun")
				.setUrl("ws://127.0.0.1:8081/wesocket").build();
		list.add(server4);
		hashFunction = Hashing.md5();
	}

	@Test
	public void test() {

		ConsistentHash<NettyServerNode> ch = new ConsistentHash<NettyServerNode>(hashFunction, 5, list);

		System.out.println(ch.get("aaaaaa"));
	}
}
