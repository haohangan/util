package curator_y.common_configuration.boot;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 专注配置
 * 
 * @author YKSE
 *
 */
@Configuration
@ComponentScan(basePackages = { "curator_y.common_configuration.buss.controller",
		"curator_y.common_configuration.buss.service", "curator_y.common_configuration.common.advice",
		"curator_y.common_configuration.curator" })
@EntityScan(basePackages = { "curator_y.common_configuration.buss.entity" })
@EnableJpaRepositories(basePackages = "curator_y.common_configuration.buss.dao")
public class ConfigurationClass {

}
