package jersey.jaspring.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
//@ComponentScan(basePackageClasses = {GreetingService.class})
@ComponentScan(basePackages={"jersey.jaspring.service"})
public class SpringAnnotationConfig {

}
