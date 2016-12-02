package demo.nboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author guihao
 * @date 2016年12月1日下午6:15:33
 * @desc 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AppServer {
    public static void main(String[] args) {
    	 SpringApplication.run(AppServer.class, args);  
	}
}
