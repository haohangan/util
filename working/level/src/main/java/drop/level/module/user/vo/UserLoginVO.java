package drop.level.module.user.vo;

import org.hibernate.validator.constraints.NotBlank;

public class UserLoginVO {
	@NotBlank(message = "登录名不能为空")
	private String name;

	@NotBlank(message = "登录密码不能为空")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
