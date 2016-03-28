package dao.redis;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import com.common.util.redis.LocalRedisConfig;
import com.dao.DictionaryDao;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月14日 下午2:35:56 类说明
 */
@ContextConfiguration(classes = { LocalRedisConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class DictionaryDaoTest {

	@Inject
	private DictionaryDao dao;

	@Inject
	private StringRedisTemplate redisTemplate;
	
	private StopWatch sw = new StopWatch();

	/**
	 * flushAll() 命令从数据库中删除所有键，而 flushDb() 仅删除当前数据库中的键
	 */
	@After
	public void flush() {
		redisTemplate.getConnectionFactory().getConnection().flushAll();
	}

	@Test
	public void test1() {
		String meaning = "this is a first meaning !";
		Long index = dao.addWordWithItsMeaningToDictionary("loop", meaning);
		// Object obj = null;
		assertNotNull("index is null", index);
		assertEquals("index is not 1L",new Long(1L), index);
	}
	
	@Test
	public void test1_5(){
		sw.start();
		for(int i = 0;i<10000;i++){
			dao.addWordWithItsMeaningToDictionary("a", (Math.random()*10)+"");
			dao.addWordWithItsMeaningToDictionary("b", (Math.random()*10)+"");
			dao.addWordWithItsMeaningToDictionary("c", (Math.random()*10)+"");
		}
		sw.stop();
		System.out.println(sw.getTotalTimeMillis());
	}
	
	@Test
	public void test2(){
		String[] keys = {"a","b","c"};
//		sw.start();
		for(String key:keys){
			System.out.println("key\t"+dao.getAllMeaningForAWord(key));
		}
//		sw.stop();
//		System.out.println(sw.getTotalTimeMillis());
	}
	
	@Test
	public void test3(){
		String[] keys = {"a","b","c"};
		dao.delete(keys);
	}
	
	@Test
	public void test4(){
		
	}
}
