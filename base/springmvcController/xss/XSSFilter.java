package com.grgbanking.aptoto.filter.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 防止xss攻击的filter<br>
 * guihao <br>
 * ghao3@grgbanking.com<br>
 */
public class XSSFilter  implements Filter {

	private FilterConfig config = null;
	// private static final String APOSTROPHE = "apostrophe";
	private static boolean no_init = true;
	private String apostrophe = "&#39;";
	// private static final String CPR = "(c) Coldbeans
	// mailto:info@servletsuite.com";
	// private static final String VERSION = "ver. 1.3";

	public void init(FilterConfig paramFilterConfig) throws ServletException {
		this.config = paramFilterConfig;
		no_init = false;
		String str = paramFilterConfig.getInitParameter("apostrophe");
		if (str != null) {
			this.apostrophe = str.trim();
		}
	}

	public void destroy() {
		this.config = null;
	}

	public FilterConfig getFilterConfig() {
		return this.config;
	}

	public void setFilterConfig(FilterConfig paramFilterConfig) {
		if (no_init) {
			no_init = false;
			this.config = paramFilterConfig;
			String str = paramFilterConfig.getInitParameter("apostrophe");
			if (str != null) {
				this.apostrophe = str.trim();
			}
		}
	}

	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,
			FilterChain paramFilterChain) throws IOException, ServletException {
		System.out.println("XSS filte-----------------");
		paramFilterChain.doFilter(new RequestWrapper((HttpServletRequest) paramServletRequest, this.apostrophe),
				paramServletResponse);
	}

}
