/**
 * 
 */
package com.hospital.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hospital.constants.HospitalConstants;
import com.hospital.model.SysUserDomain;
import com.hospital.service.ISysUserService;
import com.hospital.service.impl.SysUserServiceImpl;
import com.hospital.util.MD5Util;
import com.hospital.util.StringUtils;

@WebServlet("/userServlet.do")
public class SysUserServlet extends HttpServlet {

	private static final long serialVersionUID = -965385398527072236L;

//	ͨ�������ֱ��������¼ҳ��
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("handlerType");
		System.out.println("�������handler��" + type);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		if ("loginIn".equals(type)) {
			if (userName != null && !"".equals(userName) && password != null && !"".equals(password)) {
				ISysUserService userService = new SysUserServiceImpl();
				SysUserDomain sysUser = userService.querySysUser(userName, MD5Util.MD5(password));
				if (sysUser != null) {
					// ��¼�ɹ����û���������¼��Ϣ�浽session��ȥ
					HttpSession session = request.getSession();
					session.setAttribute(HospitalConstants.USER_CODE_SESSION_KEY, sysUser.getUserCode());
					session.setAttribute(HospitalConstants.USER_NAME_SESSION_KEY, sysUser.getUserName());
					session.setAttribute(HospitalConstants.REAL_NAME_SESSION_KEY, sysUser.getRealName());
					// ��¼�ɹ���ת����reservation_formҳ��
					// ��ʽ����
					request.setAttribute("title", "ԤԼ��");
					request.setAttribute("activeCss", "yuyue");
					// request.getRequestDispatcher("/view/reservation_form.jsp").forward(request,
					// response);
					System.out.println("��¼�ɹ���");
					response.sendRedirect(request.getContextPath() + "/orderInfoServlet.do");
				} else {
					// �ض���
					// request.getContextPath():����hospital�����ĿĿ¼
					response.sendRedirect(request.getContextPath() + "/view/login_admin.jsp");
					System.out.println("��¼ʧ�ܣ�");
				}
			}
		} else if ("editorQuery".equals(type)) {
			// ��ʽ����
			request.setAttribute("title", "�޸ĸ�����Ϣ");
			request.getRequestDispatcher("/view/user_edit.jsp").forward(request, response);
		} else {
			// ��session���󱣴��user��������
			System.out.println("��¼ʧЧ��");
			request.getSession().invalidate();// ��session������Ч
			request.getRequestDispatcher("/view/login_admin.jsp").forward(request, response);
		}
	}

//	public static void main(String[] args) {
//		ISysUserService userService = new SysUserServiceImpl();
//		Boolean flag = userService.loginCheck("admin", MD5Util.MD5("123456"));
//		System.out.println(flag);
//	}

}
