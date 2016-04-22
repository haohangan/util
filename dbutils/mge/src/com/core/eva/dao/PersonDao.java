package com.core.eva.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.StringUtils;

import com.core.eva.pojo.Person;
import com.core.eva.utils.Pager;

public class PersonDao {
	public static final QueryRunner queryRunner = new QueryRunner();
	public static final ResultSetHandler rsh = new BeanHandler<Person>(
			Person.class);
	public static final ResultSetHandler rshlist = new BeanListHandler<Person>(
			Person.class);

	/**
	 * 增
	 * 
	 * @param conn
	 * @param person
	 */
	public void insert(Connection conn, Person person) {
		String sql = "insert into person(name,age) values(?,?)";
		try {
			queryRunner.insert(conn, sql, rsh, person.getName(),
					person.getAge());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * 删
	 * 
	 * @param conn
	 * @param person
	 */
	public void delete(Connection conn, Person person) {
		String sql = "delete from person where id = ?";
		try {
			queryRunner.update(conn, sql, person.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * 改
	 * 
	 * @param conn
	 * @param person
	 */
	public void update(Connection conn, Person person) {
		String sql = "update person set name = ? , age = ? where id = ?";
		try {
			queryRunner.update(conn, sql, person.getName(), person.getAge(),
					person.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}

	}

	/**
	 * 查
	 * 
	 * @param conn
	 * @param person
	 * @param page
	 * @return
	 */
	public List<Person> queryByPage(Connection conn, Person person, Pager page) {
		String sql = getQueryString(person, page);
		List<Person> list = null;
		try {
			list = queryRunner.query(conn, sql, rshlist);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return list;
	}

	private String getQueryString(Person person, Pager page) {
		StringBuilder sb = new StringBuilder(
				"select id,name,age from person where 1 = 1");
		int start = 0;
		int num = 10;
		if (page != null) {
			start = (page.getPageIndex() - 1) * page.getPageIndex();
			num = page.getPageSize();
		}
		if (person != null) {
			if (StringUtils.isNotBlank(person.getName())) {
				sb.append(" and name = '" + person.getName() + "'");
			}
			if (person.getAge() > 0) {
				sb.append(" and age = " + person.getAge());
			}
		}
		sb.append(" limit " + start);
		sb.append("," + num);
		return sb.toString();
	}

	public static void main(String[] args) {
		// System.out.println(StringUtils.isNotBlank(null));
		// System.out.println(StringUtils.isNotBlank(""));
		// System.out.println(StringUtils.isNotBlank("   "));
		// System.out.println("***************************");
		// System.out.println(StringUtils.isNotEmpty(null));
		// System.out.println(StringUtils.isNotEmpty(""));
		// System.out.println(StringUtils.isNotEmpty("   "));
		String str = new PersonDao().getQueryString(null, null);
		System.out.println(str);
	}
}
