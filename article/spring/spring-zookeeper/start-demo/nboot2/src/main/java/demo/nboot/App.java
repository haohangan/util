package demo.nboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class);
		// ConfigurableApplicationContext context = new
		// SpringApplicationBuilder(Application.class).web(true).run(args);
		AppHelper.waitCommand();
		SpringApplication.exit(context);
		System.out.println("server stoped");
	}
}
