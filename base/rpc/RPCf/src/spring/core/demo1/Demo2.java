package spring.core.demo1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2015年11月24日 下午5:43:42 类说明
 */
public class Demo2 {

	protected static final Log log = LogFactory.getLog(Demo2.class);

	public static void main(String[] args) {
		Resource res = new ClassPathResource("spring/core/demo1/demo1.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);//初始化factory使用
		System.out.println(reader.loadBeanDefinitions(res));//初始化BeanFactory
		log.info("初始化BeanFactory结束");
		Person p = (Person) factory.getBean("Boy");
		p.setName("GH");
		p.self();
		log.info("...1415927");
	}
}

/*
 * Resource res = new ClassPathResource("bean.xml"); DefaultListableBeanFactory
 * factory= new DefaultListableBeanFactory (); XmlBeanDefinitionReader reader =
 * new XmlBeanDefinitionReader(factory); reader.loadBeanDefinitions(resource)
 */