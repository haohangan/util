package com.eva.core.db.basedao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.eva.core.db.basedao.BaseDao;
import com.eva.core.db.entity.BasePojo;

public class BaseDaoImpl<T extends BasePojo> extends BaseDao {
	private Connection conn;
	QueryRunner qr = new QueryRunner();


	public BaseDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(BasePojo t) {
		String sql = "";
		ResultSetHandler<T> rsh = new BeanHandler<T>(extracted(t));
		try {
			qr.insert(sql, rsh, "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private Class<T> extracted(BasePojo t) {
		return (Class<T>) t.getPojoType();
	}

	@Override
	public void delete(Serializable pk) {

	}

	@Override
	public void update(BasePojo t) {

	}

	@Override
	public void queryAll() {

	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
