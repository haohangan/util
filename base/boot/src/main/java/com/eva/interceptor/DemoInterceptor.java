package com.eva.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.eva.interceptor.utils.ResponseUtils;
import com.eva.model.user.authcache.AuthExame;
import com.eva.model.user.jwtutils.JWTCreateUtil;

import io.jsonwebtoken.Claims;

public class DemoInterceptor implements HandlerInterceptor {
	static boolean isCheckToken = true;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (isCheckToken) {// 是否检验token
			String token = request.getParameter("token");
			if (!StringUtils.hasLength(token)) {
				ResponseUtils.writeObject("无token", "", response);
				return false;
			}
			Claims c = null;
			try {
				c = JWTCreateUtil.parse(token);
			} catch (Exception e) {
				ResponseUtils.writeObject("非法token", "", response);
				return false;
			}
			if (!JWTCreateUtil.analyzeExpireDate(c)) {
				ResponseUtils.writeObject("token过期", "", response);
				return false;
			}

			String uri = request.getRequestURI();
			String role = c.get("role").toString();
			if (!AuthExame.exameOperation(uri, role)) {// 验证不通过
				StringBuilder sb = new StringBuilder("用户:");
				sb.append(c.get("name"));
				sb.append(",role:");
				sb.append(role);
				sb.append(" 对url=");
				sb.append(uri);
				sb.append(" 无操作权限");
				ResponseUtils.writeObject(sb.toString(), "", response);
				return false;
			}
		}
		return true;// 为true才会继续向下执行
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("请求处理之后，渲染视图之前：" + handler.getClass().getSimpleName());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("请求结束之后（清理工作）");
	}

}
