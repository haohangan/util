package spring.core.demo1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月24日 上午11:23:07
 * @desc 测试 BeanFactory
 */
@SuppressWarnings("deprecation")
public class Demo1 {
	protected static final Log log = LogFactory.getLog(Demo1.class);

	public static void main(String[] args) {
		Resource res = new ClassPathResource("spring/core/demo1/demo1.xml",
				Demo1.class.getClassLoader());
		BeanFactory fac = new XmlBeanFactory(res);
		System.out.println("是否包含(BOY)：" + fac.containsBean("Boy"));
		System.out.println("是否包含(boyImpl)：" + fac.containsBean("boyImpl"));
		System.out.println("別名：" + fac.getAliases("Boy").length);
		for(String a:fac.getAliases("Boy")){
			log.info("别名："+a);
		}
		System.out.println("prototype:" + fac.isPrototype("Boy"));
		Person p = (Person) fac.getBean(Person.class);
		p.setName("HG");
		p.self();
		log.info("sandian1415927");// AFC软件开发
	}
}
