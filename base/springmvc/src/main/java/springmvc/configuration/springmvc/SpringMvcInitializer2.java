package springmvc.configuration.springmvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月22日 下午12:47:00 类说明
 */
public class SpringMvcInitializer2 extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// return new Class<?>[] { HelloWorldAnno.class };
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { HelloWorldAnno.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
