package com.dao;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月14日 下午2:31:38 类说明
 */
/**
 * 可以使用 DictionaryDao 执行 Redis 中的操作 将 RedisTemplate（Spring Data Redis
 * 项目中的核心类）注入到了 DictionaryDao 中。清单 4 中的 StringRedisTemplate 是 RedisTemplate
 * 的一个处理字符串数据类型的子类。
 * 
 * RedisTemplate 提供了键类型操作，比如
 * ValueOperations、ListOperations、SetOperations、HashOperations 和
 * ZSetOperations。清单 4 使用 ListOperations 将一个新单词存储在 Redis 数据存储中。rightPush()
 * 操作将该单词和它的含义添加到列表末尾处。dictionaryDao.addWordWithItsMeaningToDictionary()
 * 方法返回添加到列表中的元素的索引。
 * 
 * @author Administrator
 *
 */
@Repository
public class DictionaryDao {
	@SuppressWarnings("unused")
	private static final String ALL_UNIQUE_WORDS = "all-unique-words";

	private StringRedisTemplate redisTemplate;

	@Inject
	public DictionaryDao(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public Long addWordWithItsMeaningToDictionary(String word, String meaning) {
		return redisTemplate.opsForList().rightPush(word, meaning);
	}
	
	public List<String> getAllMeaningForAWord(String word) {
		List<String> meanings = redisTemplate.opsForList().range(word, 0, 4);
		return meanings;
	}

	public void delete(String word) {
		redisTemplate.delete(word);
	}

	public void delete(String... words) {
		redisTemplate.delete(Arrays.asList(words));
	}
}
