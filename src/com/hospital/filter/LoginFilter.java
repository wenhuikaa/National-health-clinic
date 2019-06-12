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
 * ��¼����
 */
@WebFilter(filterName = "loginFilter", urlPatterns = { "*.jsp", "*.do" }, initParams = {
		@WebInitParam(name = "encoding", value = "UTF-8"),
		@WebInitParam(name = "loginJsp", value = "/view/login_admin.jsp"), // ��¼��ǰ̨�������·��
		@WebInitParam(name = "registerJsp", value = "/view/register_admin.jsp"), // ע���ǰ̨�������·��
		@WebInitParam(name = "loginDo", value = "/userServlet.do")// ��¼ע��ĺ�̨����·��
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

		//ͳһ����
		req.setCharacterEncoding(encoding);

		HttpSession session = req.getSession();

		//����û�����ʵ�¼���棬Ӧ���ǲ���Ҫ����
		String requestURI = req.getRequestURI();
		//System.out.println("�û����ڷ��ʵĽ��棺" + requestURI);
		if (requestURI.contains(loginJsp) || requestURI.contains(loginDo)||requestURI.contains(registerJsp)) {
			// ��Ҫȥ��¼����Ӧ������
			chain.doFilter(request, response);
			return;
		}

		//�����������
		Object userInfo = session.getAttribute(HospitalConstants.USER_NAME_SESSION_KEY);
		if (userInfo == null) {
			// δ��¼�û�
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
