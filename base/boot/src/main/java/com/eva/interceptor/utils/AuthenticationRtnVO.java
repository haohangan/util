package com.eva.interceptor.utils;

public class AuthenticationRtnVO {
	private String code;
	private String msg;
	private String url;

	public AuthenticationRtnVO(String msg, String url) {
		code = "Token001";
		this.msg = msg;
		this.url = url;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
