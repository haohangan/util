package com.core.eva.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.core.eva.dao.util.c3p0.MGEDataSource;
import com.core.eva.pojo.Person;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MGEdbUtils {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String USERNAME = "root";
	public static final String USERPWD = "tiger";
	public static final String URL = "jdbc:mysql://localhost:3306/mywork?useUnicode=true&characterEncoding=utf-8";
	public static final QueryRunner queryRunner = new QueryRunner();

	public static Connection getConn() throws SQLException {
		// DbUtils.loadDriver(DRIVER);
		// Connection conn = DriverManager.getConnection(URL, USERNAME,
		// USERPWD);
		Connection conn = MGEDataSource.getDataSource().getConnection();
		return conn;
	}

	/**
	 * 通用的查询方法，仅测试用
	 * 
	 * @param conn
	 * @param sql
	 * @param rsh
	 * @return
	 */
	public static List<?> queryBean(Connection conn, String sql,
			ResultSetHandler rsh) {
		List<?> list = null;
		try {
			list = queryRunner.query(conn, sql, rsh);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return list;
	}

	/**
	 * 通用插入方法，仅测试用
	 * 
	 * @param sql
	 * @param conn
	 * @param rsh
	 */
	public static void insert(String sql, Connection conn, ResultSetHandler rsh) {
		try {
			queryRunner.insert(conn, sql, rsh);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

}
