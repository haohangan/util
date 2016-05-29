package com.eva.model.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eva.dao.UserRepository;
import com.eva.entity.AppUser;
import com.eva.model.user.bussvo.LoginInputVO;
import com.eva.model.user.bussvo.LoginRtnVO;
import com.eva.model.user.jwtutils.JWTCreateUtil;

@RestController
@RequestMapping("/user")
public class UserLoginController {

	static enum LoginState {
		SUCCESS("0x000"),NO_SUCH_NAME("0x001"), ERROR_PWD("0x002"), SYSTEM_ERROR("0x003"),ERROR("0x004");

		private String message;

		LoginState(String idx) {
			this.message = idx;
		}

		public String getMsg() {
			return message;
		}
	}

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("login.do")
	public LoginRtnVO login(@Validated LoginInputVO lvo) {
//		if (bindingResult.hasErrors()) {,BindingResult bindingResult
//			StringBuilder sb = new StringBuilder("[");
//			bindingResult.getAllErrors().forEach((t)->{
//				sb.append(t.getObjectName());
//				sb.append(":");
//				sb.append(t.getDefaultMessage());
//				sb.append(",");
//			});
//			sb.delete(sb.lastIndexOf(","), sb.length());
//			sb.append("]");
//			return new LoginRtnVO(LoginState.ERROR.getMsg(),sb.toString(),false);
//        }
		
		// 通过数据库验证用户名和密码是否匹配
		List<AppUser> users = userRepository.findByName(lvo.getName());
		if (users.isEmpty()) {
			return new LoginRtnVO(LoginState.NO_SUCH_NAME.getMsg(), "用户名错误", false);
		}
		if (users.size() > 1) {
			return new LoginRtnVO(LoginState.SYSTEM_ERROR.getMsg(), "用户重复", false);
		}
		// 校验密码
		AppUser appUser = users.get(0);
		if (!appUser.getPwd().equals(lvo.getPwd())) {
			return new LoginRtnVO(LoginState.ERROR_PWD.getMsg(), "密码错误", false);
		}
		StringBuilder sb = new StringBuilder();
		appUser.getRoles().forEach((t) -> {
			sb.append(t.getRoleName());
			sb.append(",");
		});
		sb.delete(sb.lastIndexOf(","), sb.length());
		String token = JWTCreateUtil.create(lvo.getName(), sb.toString());
		return new LoginRtnVO(LoginState.SUCCESS.getMsg(), token, true);
	}

	// StringBuilder sb = new StringBuilder("Basic[");
	// sb.append(token);
	// sb.append("]");
	// resp.setHeader("Authorization", sb.toString());

//	public static void main(String[] args) {
//		Key key = MacProvider.generateKey();
//
//		String s = Jwts.builder().setSubject("Joe").signWith(SignatureAlgorithm.HS512, key).compact();
//		System.out.println(s);
//		String a = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb2UifQ.HwLCczhQoAHeSYnnjRjKZC8A1JqOqHuQY1Pelk27vhA-Gw2Bc25g1KDity7GRZfc3zJyB4X9q2PNEtcFrdW2pw";
//		System.out.println(s == a);
//		System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(s).getBody().getSubject());
//		System.out.println(LoginState.ERROR_PWD.getMsg());
//	}
}
