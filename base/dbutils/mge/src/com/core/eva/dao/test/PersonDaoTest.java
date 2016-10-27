package com.core.eva.dao.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.core.eva.dao.PersonDao;
import com.core.eva.dao.util.MGEdbUtils;
import com.core.eva.pojo.Person;
import com.core.eva.utils.Pager;

public class PersonDaoTest {
	public static Connection conn = null;
	public static PersonDao dao = new PersonDao();

	public static void main(String[] args) throws SQLException {
		conn = MGEdbUtils.getConn();
		testQuery();
	}
	
	public static void testInsert(){
		Person p = new Person();
		p.setId(4);
		p.setAge(29);
		p.setName("eva");
		dao.insert(conn, p);
	}

	public static void testDelete() throws SQLException {
		Person p = new Person();
		p.setId(4);
		dao.delete(conn, p);
	}

	public static void testUpdate() throws SQLException {
		Person p = new Person();
		p.setId(3);
		p.setName("廣電");
		p.setAge(18);
		dao.update(conn, p);
	}
	
	public static void testQuery(){
		Person p = new Person();
		// p.setId(3);
		// p.setName("廣電");
		// p.setAge(18);
		Pager page = new Pager();
		page.setPageIndex(1);
		page.setPageSize(10);
		List<Person> list = dao.queryByPage(conn, p, page);
		System.out.println(list);
	}
}
