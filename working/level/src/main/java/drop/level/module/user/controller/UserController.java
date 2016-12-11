package drop.level.module.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import drop.level.module.common.vo.BussResult;
import drop.level.module.user.vo.UserLoginVO;

@RestController
@RequestMapping("user")
public class UserController {

	// @RequestMapping(path = "login", method = RequestMethod.POST)
	public BussResult login(UserLoginVO loginvo) {
		BussResult result = null;
		if (loginvo.getName().equals(loginvo.getPassword())) {
			result = new BussResult.Builder().code("login").msg("登陆成功，获取token").success().build();
		} else {
			result = new BussResult.Builder().code("login").msg("登陆失败，重新填写账号密码").fail().build();
		}
		return result;
	}

	@RequestMapping(path = "status", method = { RequestMethod.GET })
	public String login() {
		return "login : logined";
	}
}
