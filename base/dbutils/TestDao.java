package demo.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import demo.entity.Test;

/**
 * @author guihao E-mail:1242188036@qq.com
 * @version 创建时间：2016年2月2日 下午3:09:15 类说明
 */
public class TestDao {
	QueryRunner qr = new QueryRunner();
	ResultSetHandler<List<Test>> rsh = new BeanListHandler<Test>(Test.class);
	ResultSetHandler<Test> h = new BeanHandler<Test>(Test.class);

	private Connection conn;

	public TestDao(Connection conn) {
		this.conn = conn;
	}

	// select * from test
	public List<Test> listAll() {
		String sql = "select id,name,age from test";
		List<Test> list = null;
		try {
			list = qr.query(conn, sql, rsh);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return list;
	}

	public void insertObj(Test t) {
		String sql = "insert into test(id,name,age) values(?,?,?)";
		try {
			// qr.insert(conn, sql, h);
			qr.insert(conn, sql, h, t.getId(), t.getName(), t.getAge());
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	public void updateObj(Test t) {
		String sql = "update test set name = ?,age = ? where id = ?";
		try {
			qr.update(conn, sql, t.getName(), t.getAge(), t.getId());
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	public Test getObj(Serializable pk) {
		String sql = "select id,name,age from test where id = ?";
		Test t = null;
		try {
			t = qr.query(conn, sql, h, pk);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return t;
	}

	public void insertbatch(List<Test> list) throws SQLException {
		Object[][] params = new Object[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			Test t = list.get(i);
			params[i][0] = t.getId();
			params[i][1] = t.getName();
			params[i][2] = t.getAge();
		}
		String sql = "insert into test(id,name,age) values(?,?,?)";
		Savepoint sp = null;
		try {
			sp = conn.setSavepoint();
			qr.insertBatch(conn, sql, h, params);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback(sp);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
	public void deleteObj(Serializable pk){
		String sql ="delete from test where id = ?";
		try {
			qr.update(conn, sql, pk);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
	public void deleteTableRows(){
		String sql ="delete from test";
		try {
			qr.update(conn, sql);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtils.closeQuietly(conn);
		}
	}

}
