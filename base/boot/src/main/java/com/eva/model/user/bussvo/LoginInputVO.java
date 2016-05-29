package com.eva.model.user.bussvo;

import javax.validation.constraints.Size;

public class LoginInputVO {

	// @NotNull(message="登陆名不能为空")
	// @Size(min = 1, max = 30,message="登陆名过长")
//	@NotEmpty(message = "登陆名不能为空")
	@Size(min = 1, max = 30, message = "登陆名长度应为：1 到 30 个字符之间")
	private String name;

	// @NotNull(message="密码不能为空")
	// @Size(min = 1, max = 30,message="密码过长")
//	@NotEmpty(message = "密码不能为空")
	@Size(min = 1, max = 30, message = "登陆密码长度应为：1 到 30 个字符之间")
	private String pwd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
