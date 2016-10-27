package com.core.eva.dao.util.c3p0;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String USERPWD = "tiger";
	private static final String URL = "jdbc:mysql://localhost:3306/mywork?useUnicode=true&characterEncoding=utf-8";

	public static ComboPooledDataSource cdps = null;

	// static {
	// System.out.println("开始加载DataSource*****C3P0");
	// cdps = new ComboPooledDataSource();
	// try {
	// cdps.setDriverClass(DRIVER);
	// } catch (PropertyVetoException e) {
	// e.printStackTrace();
	// }
	// cdps.setUser(USERNAME);
	// cdps.setPassword(USERPWD);
	// cdps.setJdbcUrl(URL);
	// // 参数配置
	// cdps.setMaxStatements(150);
	// cdps.setMinPoolSize(5);
	// cdps.setAcquireIncrement(3);
	// cdps.setMaxPoolSize(90);
	// cdps.setMaxIdleTime(1800);
	// cdps.setMaxStatementsPerConnection(20);
	// // 参数配置
	// System.out.println("开始加载DataSource*****C3P0");
	// }

	private static ComboPooledDataSource getDataSource() {
		return cdps;
	}

	public static void main(String[] args) throws PropertyVetoException,
			SQLException {
		// ComboPooledDataSource cpds = new ComboPooledDataSource();
		// cpds.setDriverClass(DRIVER);
		// cpds.setUser(USERNAME);
		// cpds.setPassword(USERPWD);
		// cpds.setJdbcUrl(URL);
		// // 参数配置
		// cpds.setMaxStatements(150);
		// cpds.setMinPoolSize(5);
		// cpds.setAcquireIncrement(3);
		// cpds.setMaxPoolSize(90);
		// cpds.setMaxIdleTime(1800);
		// cpds.setMaxStatementsPerConnection(20);
		// // 参数配置
		// int i = 0;
		// for (;;) {
		// System.out.println("i:" + (i++));
		// Connection conn = cpds.getConnection();
		// // System.out.println(conn + "\t" + conn.getCatalog());
		// if (i > 96) {
		// break;
		// }
		// }
		// System.out.println("结束");

		ComboPooledDataSource cpds = MGEDataSource.getDataSource();
		System.out.println(cpds);
		Connection conn = cpds.getConnection();
		System.out.println(conn + "\t" + conn.getCatalog());
	}
}
