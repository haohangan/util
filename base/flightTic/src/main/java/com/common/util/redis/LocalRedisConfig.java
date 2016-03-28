package com.common.util.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月14日 上午11:49:02 类说明
 */
/**
 * RedisConnectionFactory 是一个用来与 Redis 建立连接的线程安全的连接工厂，RedisConnection 是连接到 Redis
 * 的一个短期、非线程安全的连接。RedisConnection 提供了与 Redis 命令的一对一映射，而 RedisConnectionFactory
 * 提供了有助于消除样板代码的便捷方法。RedisConnectionFactory 使不同 Redis 客户端 API 之间的切换就像定义一个 bean
 * 那么简单。我们将对样例应用程序使用 JedisConnectionFactory，但也可使用其他任何 ConnectionFactory 变体。
 * 
 * @author Administrator
 *
 */
/**
 * LocalRedisConfig 定义了 JedisConnectionFactory 和 SpringRedisTemplate
 * bean。SpringRedisTemplate 是 RedisTemplate 的一个处理字符串的特殊版本。
 * 
 * @author Administrator
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.dao" })
public class LocalRedisConfig {

	@Bean
	public RedisConnectionFactory jedisConnectionFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		// poolConfig.setMaxTotal(10); 有默认值，
		// poolConfig.setMaxIdle(5);
		poolConfig.setMinIdle(1);// 默认值为0，现修改为1：最小等待

		poolConfig.setTestOnReturn(true);
		poolConfig.setTestOnBorrow(true);
		JedisConnectionFactory factory = new JedisConnectionFactory(poolConfig);
		return factory;
	}

	@Bean
	public StringRedisTemplate redisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate(
				jedisConnectionFactory());
		return template;
	}
}
