package dao.redis;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.common.util.redis.LocalRedisConfig;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月14日 下午2:27:12
 * 类说明
 */
@ContextConfiguration(classes = LocalRedisConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LocalRedisConfigTest {
    @Inject
    private JedisConnectionFactory jedisConnectionFactory;
    
    @Inject
    private StringRedisTemplate redisTemplate;
    
    @Test
    public void test1(){
    	assertNotNull(jedisConnectionFactory);
    }
    
    @Test
    public void test2(){
    	assertNotNull(redisTemplate);
    }
}
