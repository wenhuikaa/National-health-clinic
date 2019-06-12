package com.hospital.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hospital.constants.HospitalConstants;

/*
 * 登录拦截
 */
@WebFilter(filterName = "loginFilter", urlPatterns = { "*.jsp", "*.do" }, initParams = {
		@WebInitParam(name = "encoding", value = "UTF-8"),
		@WebInitParam(name = "loginJsp", value = "/view/login_admin.jsp"), // 登录的前台界面访问路径
		@WebInitParam(name = "registerJsp", value = "/view/register_admin.jsp"), // 注册的前台界面访问路径
		@WebInitParam(name = "loginDo", value = "/userServlet.do")// 登录注册的后台访问路径
})
public class LoginFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String encoding = filterConfig.getInitParameter("encoding");
		String loginJsp = filterConfig.getInitParameter("loginJsp");
		String registerJsp=filterConfig.getInitParameter("registerJsp");
		String loginDo = filterConfig.getInitParameter("loginDo");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		//统一编码
		req.setCharacterEncoding(encoding);

		HttpSession session = req.getSession();

		//如果用户想访问登录界面，应该是不需要拦截
		String requestURI = req.getRequestURI();
		//System.out.println("用户现在访问的界面：" + requestURI);
		if (requestURI.contains(loginJsp) || requestURI.contains(loginDo)||requestURI.contains(registerJsp)) {
			// 想要去登录，不应该拦截
			chain.doFilter(request, response);
			return;
		}

		//否则进行拦截
		Object userInfo = session.getAttribute(HospitalConstants.USER_NAME_SESSION_KEY);
		if (userInfo == null) {
			// 未登录用户
			resp.sendRedirect(req.getContextPath() + "/view/login_admin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
