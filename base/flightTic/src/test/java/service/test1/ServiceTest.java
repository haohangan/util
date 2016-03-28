package service.test1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gh.service.Test1;
import com.gh.service.Test2;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月23日 下午3:54:20 类说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring2.xml" })
public class ServiceTest {
	@Autowired
	// @Qualifier("logProxy")
	Test1 test1;
	
	@Autowired
	Test2 test2;

	@Test
	public void test() throws Exception {
		// test1.test1("gh");
		test1.test2("小鸡", "18");
	}
	
	@Test
	public void test2() throws Exception {
		// test1.test1("gh");
		test2.test2("小鸡", "18");
		test2.aTest();
	}
}
