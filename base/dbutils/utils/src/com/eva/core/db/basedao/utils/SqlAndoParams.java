package com.eva.core.db.basedao.utils;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class SqlAndoParams {
	private String sql;
	private List<Object> paramArr;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void add(Object obj) {
		if (paramArr == null) {
			paramArr = new ArrayList<Object>();
		}
		paramArr.add(obj);
	}

	public List<Object> getParamArr() {
		return paramArr;
	}

	// public void printparasm() {
	// for(Object obj : paramArr){
	// System.out.println(obj);
	// }
	// }
}
