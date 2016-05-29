package com.eva.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.eva.service.InitService;

/**
 * 微服务
 * 
 * @author root
 *
 */
@SpringBootApplication
public class App {

	/**
	 * 修改端口
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			container.setPort(80);
			// container.setSsl();
			// container.addInitializers(null);
		});
	}

	// @Bean(name = "messageSource")
	// public ReloadableResourceBundleMessageSource messageSource() {
	// ReloadableResourceBundleMessageSource messageBundle = new
	// ReloadableResourceBundleMessageSource();
	// messageBundle.setBasename("classpath:messages/messages");
	// messageBundle.setDefaultEncoding("UTF-8");
	// return messageBundle;
	// }

	// @Bean
	// public Validator configurationPropertiesValidator() {
	// return new SamplePropertiesValidator();
	// }

	public static void main(String[] args) {
		// System.getProperties().put( "server.port", 80);//修改端口
		ApplicationContext ctx = SpringApplication.run(App.class, args);
		// ApplicationContext ctx = new SpringApplicationBuilder(App.class)
		// .profiles("app").run(args);

		System.out.println("spring boot 初始化成功");
		System.out.println("初始化权限认证系统");
		InitService service = ctx.getBean(InitService.class);
		service.initAuthCache();
		System.out.println("初始化权限认证结束");

	}
}
