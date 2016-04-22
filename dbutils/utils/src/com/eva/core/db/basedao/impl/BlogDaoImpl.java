package com.eva.core.db.basedao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.eva.core.db.basedao.utils.DaoHelper;
import com.eva.core.db.basedao.utils.SqlAndoParams;
import com.eva.core.db.entity.Blog;

public class BlogDaoImpl {
	private Connection conn;
	QueryRunner qr = new QueryRunner();
	ResultSetHandler<Blog> rsh = new BeanHandler<Blog>(Blog.class);
	ResultSetHandler<List<Blog>> rshlist = new BeanListHandler<Blog>(Blog.class);

	public BlogDaoImpl() {
	}

	public BlogDaoImpl(Connection conn) {
		this.setConn(conn);
	}

	/**
	 * #增
	 * 
	 * @param blog
	 */
	public void insert(Blog blog) {
		String sql = "INSERT INTO `blog` (`bname`, `buser`, `bdate`, `content`, `access`) VALUES (?, ?, ?, ?, ?)";
		try {
			qr.insert(sql, rsh, blog.getBname(), blog.getBuser(),
					blog.getBdate(), blog.getContent(), blog.getAccess());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * #删
	 * 
	 * @param pk
	 */
	public void delete(Serializable pk) {
		String sql = "DELETE FROM `blog` WHERE `bid`= ?";
		try {
			qr.update(conn, sql, pk);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * #改
	 * 
	 * @param blog
	 */
	public void update(Blog blog) {
		String sql = "UPDATE `blog` SET `bname`=?, `buser`=?, `bdate`=?, `content`=?, `access`=? WHERE `bid`=?";
		try {
			qr.update(conn, sql, blog.getBname(), blog.getBuser(),
					blog.getBdate(), blog.getContent(), blog.getAccess(),
					blog.getBid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * 会导致错误
	 * 
	 * @param blog
	 * @return
	 */
	@Deprecated
	public List<Blog> query2(Blog blog) {
		List<Blog> list = null;
		String sql = "SELECT bid,bname,buser,bdate,content,access FROM blog where bid = ?";
		try {
			list = qr.query(conn, sql, rshlist, blog.getBid());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return list;
	}

	/**
	 * #查
	 * 
	 * @param blog
	 * @return
	 */
	public List<Blog> query(Blog blog) {
		List<Blog> list = null;
		String sql = "SELECT bid,bname,buser,bdate,content,access FROM blog where 1 = 1";
		try {
			sql = DaoHelper.paramSql(sql, blog, Blog.getMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("sql:" + sql);
		try {
			list = qr.query(conn, sql, rshlist);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return list;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
