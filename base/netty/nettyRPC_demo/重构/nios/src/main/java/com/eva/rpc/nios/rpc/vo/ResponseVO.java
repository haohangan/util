package com.eva.rpc.nios.rpc.vo;

import java.io.Serializable;

public class ResponseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	static final String DEFAULT_SUCCESS = "请求成功";
	static final String DEFAULT_FAILED = "请求失败";

	private boolean success;
	private String msg;
	private Object result;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public ResponseVO TRUE() {
		this.success = Boolean.TRUE;
		this.msg = DEFAULT_SUCCESS;
		return this;
	}

	public ResponseVO FALSE() {
		this.success = Boolean.FALSE;
		this.msg = DEFAULT_FAILED;
		return this;
	}

	@Override
	public String toString() {
		return "ResponseVO [success=" + success + ", msg=" + msg + ", result=" + result + "]";
	}
}
