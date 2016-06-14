package com.eva.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionPool extends ObjectPool<Connection> {
	private String dsn, usr, pwd;

	public JDBCConnectionPool(String driver, String dsn, String usr, String pwd) {
		super();
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dsn = dsn;
		this.usr = usr;
		this.pwd = pwd;
	}

	@Override
	protected Connection create() {
		try {
			return (DriverManager.getConnection(dsn, usr, pwd));
		} catch (SQLException e) {
			e.printStackTrace();
			return (null);
		}
	}

	@Override
	public boolean validate(Connection t) {
		try {
			return (!((Connection) t).isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
			return (false);
		}
	}

	@Override
	public void expire(Connection t) {
		try {
			((Connection) t).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
