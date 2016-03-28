package spring.core.demo1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version ����ʱ�䣺2015��11��24�� ����5:43:42 ��˵��
 */
public class Demo2 {

	protected static final Log log = LogFactory.getLog(Demo2.class);

	public static void main(String[] args) {
		Resource res = new ClassPathResource("spring/core/demo1/demo1.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);//��ʼ��factoryʹ��
		System.out.println(reader.loadBeanDefinitions(res));//��ʼ��BeanFactory
		log.info("��ʼ��BeanFactory����");
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