package com.eva.core.db.entity;

import java.util.HashMap;
import java.util.Map;

public class Blog extends BasePojo {
	private static final long serialVersionUID = 8562135024831531283L;

	private Integer bid;
	private String bname;
	private String buser;
	private String bdate;
	private String content;
	private Integer access;

	private static Map<String, String> map = new HashMap<String, String>();

	static {
		map.put("bid", "bid");
		map.put("bname", "bname");
		map.put("buser", "buser");
		map.put("bdate", "bdate");
		map.put("content", "content");
		map.put("access", "access");
	}

	public static String getDBField(String key) {
		return map.get(key);
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBuser() {
		return buser;
	}

	public void setBuser(String buser) {
		this.buser = buser;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

	public Class<?> getPojoType() {
		return Blog.class;
	}

	public static Map<String, String> getMap() {
		return map;
	}

}
