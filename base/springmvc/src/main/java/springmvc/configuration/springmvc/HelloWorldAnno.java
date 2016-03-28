package springmvc.configuration.springmvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年3月22日 上午10:35:37 类说明
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "springmvc.controller")
public class HelloWorldAnno {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	// ContentNegotiatingViewResolver
	@Bean
	public View defaultView() {
		View view = new MappingJackson2JsonView();
		return view;
	}
	
	
}
