package com.core.eva.dao.util.c3p0;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MGEDataSource {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String USERPWD = "tiger";
	private static final String URL = "jdbc:mysql://localhost:3306/mywork?useUnicode=true&characterEncoding=utf-8";

	public static ComboPooledDataSource cdps = null;

	static {
		System.out.println("开始加载DataSource*****C3P0");
		cdps = new ComboPooledDataSource();
		try {
			cdps.setDriverClass(DRIVER);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		cdps.setUser(USERNAME);
		cdps.setPassword(USERPWD);
		cdps.setJdbcUrl(URL);
		// 参数配置
		cdps.setMaxStatements(150);
		cdps.setMinPoolSize(5);
		cdps.setAcquireIncrement(3);
		cdps.setMaxPoolSize(90);
		cdps.setMaxIdleTime(1800);
		cdps.setMaxStatementsPerConnection(20);
		// 参数配置
		System.out.println("开始加载DataSource*****C3P0");
	}

	public static ComboPooledDataSource getDataSource() {
		return cdps;
	}

}
