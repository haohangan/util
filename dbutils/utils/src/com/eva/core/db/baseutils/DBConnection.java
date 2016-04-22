package com.eva.core.db.baseutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

public class DBConnection {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String USERPWD = "tiger";
	private static final String URL = "jdbc:mysql://localhost:3306/mywork?useUnicode=true&characterEncoding=utf-8";

	/**
	 * 获得单个数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConn(){
		DbUtils.loadDriver(DRIVER);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, USERPWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 留作扩展
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getPooledConn() throws SQLException {
		return null;
	}
}
