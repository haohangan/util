package org.eva.core.boot;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"org.eva.core.*.controller","org.eva.core.*.service.impl","org.eva.core.common.advice"})
@EnableJpaRepositories(basePackages = "org.eva.core.*.dao")
@EntityScan(basePackages = "org.eva.core.*.entity")
public class ConfigurationBeans {

}
