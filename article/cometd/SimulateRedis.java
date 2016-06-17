package com.grgbanking.aptoto.controller.cometd;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.grgbanking.aptoto.cometd.BayeuxJavaClient;
import com.grgbanking.aptoto.controller.cometd.vo.CometdMessage;

public class SimulateRedis {
	private final ConcurrentMap<String, CometdMessage> redis = new ConcurrentHashMap<>();
	BayeuxJavaClient client;
	private final Set<CometdMessage> mangoDB = new HashSet<>();

	public void put(CometdMessage msg) {
		redis.putIfAbsent(msg.getMsgId(), msg);
		System.out.println("正常信息保存到redis数据库中");
	}

	public void remove(String msgId) {
		synchronized (redis.get(msgId)) {
			CometdMessage todo = redis.get(msgId);
			redis.remove(msgId);
			client.sendNoticeMessage(todo.toSuccessNoticeMap());
		}

	}

	public void startDaemon() {
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(10);
		try {
			client = new BayeuxJavaClient("http://localhost:8483/cometd/cometd");
		} catch (Exception e) {
		}

		pool.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				Set<Map.Entry<String, CometdMessage>> set = redis.entrySet();
				for (Map.Entry<String, CometdMessage> entry : set) {
					CometdMessage todo = entry.getValue();
					Long expire = todo.getTime();
					Long now = System.currentTimeMillis();
					System.out.println("比较是否过期："+now+" > "+expire+"?");
					String key = entry.getKey();
					if (now.compareTo(expire) > 0) {// 超时
						redis.remove(key);// 移除
						mangoDB.add(todo);// 移入
						client.sendNoticeMessage(todo.toFailureNoticeMap());
						System.out.println("反馈信息保存到mangoDB数据库中");
					}
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
}
