package com.eva.boot;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.eva.advice","com.eva.controller","com.eva.model.*.controller","com.eva.service.impl"})
@EnableJpaRepositories(basePackages = "com.eva.dao")
@EntityScan(basePackages = "com.eva.entity")
public class ConfigurationBeans {
	// 专注于扫描package
}
