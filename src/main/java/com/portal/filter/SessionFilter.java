package com.portal.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionFilter implements Filter {
	
	private static Log log = LogFactory.getLog(SessionFilter.class);

	private String loginOutUrl;
	private String exceptUrl;
	private String exceptSource;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String servletPath = request.getServletPath();

		if (servletPath.contains(loginOutUrl)) {
			chain.doFilter(req, res);
			return;
		}

		String exceptUrlArray[] = exceptUrl.split(",");
		for (int i = 0; i < exceptUrlArray.length; i++) {
			if (servletPath.contains(exceptUrlArray[i])) {
				chain.doFilter(req, res);
				return;
			}
		}
		
		String exceptSourceArray[] = exceptSource.split(",");
		for (int i = 0; i < exceptSourceArray.length; i++) {
			if (servletPath.contains(exceptSourceArray[i])) {
				chain.doFilter(req, res);
				return;
			}
		}

		Object sessionObj = request.getSession().getAttribute("cUserSession");
		if (sessionObj == null) {
			log.info("user not login");
			log.info("servletPath = " + servletPath);
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + loginOutUrl);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		loginOutUrl = cfg.getInitParameter("loginOutUrl");
		exceptUrl = cfg.getInitParameter("exceptUrl");
		exceptSource = cfg.getInitParameter("exceptSource");
	}

}
