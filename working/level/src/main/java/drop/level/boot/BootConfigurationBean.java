package drop.level.boot;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = { "drop.level.module.*.controller", "drop.level.module.*.service" })
@EnableJpaRepositories(basePackages = "drop.level.module.*.repository")
@EntityScan(basePackages = "drop.level.module.*.entity")
public class BootConfigurationBean {
	// auto configuration
}
