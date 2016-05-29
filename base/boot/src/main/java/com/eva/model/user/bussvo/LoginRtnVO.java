package com.eva.model.user.bussvo;

public class LoginRtnVO {
	String code;//编号
	String message;//内容
	boolean success;//是否登陆成功

	public LoginRtnVO(String code, String message, boolean success) {
		this.code = code;
		this.message = message;
		this.success = success;
	}
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
