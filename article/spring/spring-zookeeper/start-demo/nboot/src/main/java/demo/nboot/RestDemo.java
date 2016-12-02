package demo.nboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author guihao
 * @date 2016年12月2日下午3:11:07
 * @desc 
 */
@Configuration
public class RestDemo {
	@Value("${spring.application.name:testZookeeperApp}")
	private String appName;
	
	@Autowired
	RestTemplate rest;
	
	@Bean
	@LoadBalanced
	RestTemplate loadBalancedRestTemplate() {
		return new RestTemplate();
	}
	
	public String rt() {
		return this.rest.getForObject("http://" + this.appName + "/hi", String.class);
	}
}
