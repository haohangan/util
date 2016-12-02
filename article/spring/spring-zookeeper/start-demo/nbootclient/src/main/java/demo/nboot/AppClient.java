package demo.nboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guihao
 * @date 2016年12月2日上午11:55:52
 * @desc
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
public class AppClient {
	@Autowired
	private LoadBalancerClient loadBalancer;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private Client client;

	@RequestMapping("/discovery")
	public Object discovery(String name) {
		System.out.println(loadBalancer.choose(name));
		return "discovery";
	}

	@RequestMapping("/client")
	public Object client() {
		return client.demo();
	}

	@RequestMapping("/all")
	public Object all() {
		List<String> services = discoveryClient.getServices();
		if (services != null && !services.isEmpty()) {
			for (String name : services) {
				List<ServiceInstance> instances = discoveryClient.getInstances(name);
				for (ServiceInstance inst : instances) {
					System.out.print("app:" + name + "-");
					System.out.print("service id:" + inst.getServiceId() + "\t");
					System.out.print("uri:" + inst.getUri() + "\t");
					System.out.println();
				}
			}
		}
		return "all";
	}

	public static void main(String[] args) {
		SpringApplication.run(AppClient.class, args);
	}
}
