package com.grgbanking.aptoto.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

import com.grgbanking.aptoto.model.entity.users.SysUserEntity;
import com.grgbanking.aptoto.services.MemberOnlineStatusService;

/**
 * 用户退出系统或者超时退出系统时，主动删除用户登录状态表中的数据 <br>
 * guihao <br>
 * ghao3@grgbanking.com<br>
 */
public class SessionTimeoutlistener implements HttpSessionListener, ApplicationContextAware {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	static final String SYS_USER = "sysUser";

	private MemberOnlineStatusService memberOnlineStatusService;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
//		System.out.println("session 创建：=========================");
		logger.debug("session 创建");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.debug("session 失效");
		synchronized (this) {
			HttpSession session = se.getSession();
			SysUserEntity sysUser = (SysUserEntity) session.getAttribute(SYS_USER);
			if (sysUser != null) {
				String idToDelete = sysUser.getId();
				logger.debug("session 超时退出登录");
				memberOnlineStatusService.deleteById(idToDelete);
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (applicationContext instanceof WebApplicationContext) {
			((WebApplicationContext) applicationContext).getServletContext().addListener(this);
		} else {
			// Either throw an exception or fail gracefully, up to you
			throw new RuntimeException("Must be inside a web application context");
		}
	}

	public MemberOnlineStatusService getMemberOnlineStatusService() {
		return memberOnlineStatusService;
	}

	public void setMemberOnlineStatusService(MemberOnlineStatusService memberOnlineStatusService) {
		this.memberOnlineStatusService = memberOnlineStatusService;
	}

}
