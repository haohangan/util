package com.core.eva.dao.util.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.core.eva.dao.util.MGEdbUtils;
import com.core.eva.pojo.Person;

public class DBUtilsTest {
	public static void main(String[] args) throws SQLException {
		testQuery();
		// testInsert();
	}

	/**
	 * 测试查询方法
	 * 
	 * @throws SQLException
	 */
	public static void testQuery() throws SQLException {
		Connection conn = MGEdbUtils.getConn();
		// System.out.println(conn);
		ResultSetHandler rsh = new BeanListHandler<Person>(Person.class);
		String sql = "SELECT * FROM mywork.person";
		List<Person> list = (List<Person>) MGEdbUtils.queryBean(conn, sql, rsh);
		System.out.println(list);
	}

	/**
	 * 测试通用insert方法
	 * 
	 * @throws SQLException
	 */
	public static void testInsert() throws SQLException {
		ResultSetHandler rsh = new BeanListHandler<Person>(Person.class);
		Person p = new Person();
		p.setAge(56);
		p.setName("小芳");
		String sql = "insert into person(age,name) values(19,'小芳')";
		Connection conn = MGEdbUtils.getConn();
		MGEdbUtils.insert(sql, conn, rsh);
	}
}
