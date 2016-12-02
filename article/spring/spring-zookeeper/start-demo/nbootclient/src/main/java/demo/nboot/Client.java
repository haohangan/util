package demo.nboot;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Configuration
@FeignClient("testZookeeperApp")
public interface Client {

	@RequestMapping(path = "/demo", method = RequestMethod.GET)
	String demo();
}
