//package com.grgbanking.aptoto.config;
//
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import com.grgbanking.aptoto.config.database.mongodb.MongoDBConfig;
//import com.grgbanking.aptoto.config.database.redis.RedisConfig;
//
//public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return new Class<?>[] { ScanConfiguration.class, MongoDBConfig.class, RedisConfig.class };
//	}// BayeuxJavaClient.class,
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return new Class<?>[] { ControllerConfiguration.class };
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		return new String[] { "/" };
//	}
//
//}
