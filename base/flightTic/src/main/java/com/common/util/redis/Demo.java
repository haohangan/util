package com.common.util.redis;

import redis.clients.jedis.Jedis;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月14日 上午10:59:51 类说明
 */
/**
 * 关键优势
 * 
 * Redis 的优势包括它的速度、它对富数据类型的支持、它的操作的原子性，以及它的通用性：
 * 
 * Redis 非常快。它每秒可执行约 100,000 个 SET 以及约 100,000 个 GET 操作。您可以使用 redis-benchmark
 * 实用程序在自己的机器上对它的性能进行基准测试。（redis-benchmark 模拟在它发送总共 M 个查询的同时，N 个客户端完成的 SET/GET
 * 操作。） Redis 对大多数开发人员已知道的大多数数据类型提供了原生支持，这使得各种问题得以轻松解决。经验会告诉您哪个问题最好由何种数据类型来处理。
 * 因为所有 Redis 操作都是原子性的，所以多个客户端会并发地访问一个 Redis 服务器，获取相同的更新值。 Redis
 * 是一个多效用工具，对许多用例很有用，这些用例包括缓存、消息队列（Redis 原生支持发布/订阅）、短期应用程序数据（比如 Web 会话、Web
 * 页面命中计数）等。
 * 
 * @author Administrator
 *
 */
public class Demo {
	/**
	 * 字符串 列表 集合 有序集 哈希值
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("localhost");
		String is = jedis.ping();
		System.out.println("Server is running: " + is);
		// jedis.set("a", "123");
		// System.out.println(jedis.get("a"));
		// jedis.del("a");
		// System.out.println(jedis.del("a"));
	}
}
