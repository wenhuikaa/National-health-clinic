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

//	通过服务端直接跳到登录页面
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("handlerType");
		//System.out.println("请求参数handler是" + type);

		if("register".equals(type)) {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String realName = request.getParameter("realName");
			String userType = request.getParameter("userType");
			System.out.println(userType);
			
			if (userName != null && !"".equals(userName) && password != null && !"".equals(password)) {
				SysUserDomain sysUser = new SysUserDomain();
				sysUser.setUserName(userName);
				sysUser.setPassword(MD5Util.MD5(password));
				sysUser.setRealName(realName);
				sysUser.setUserStatus(1);
				if("管理员".equals(userType)) {
					sysUser.setUserType(1);
				}else {
					sysUser.setUserType(2);
				}
				sysUser.setDataState(1);
				
				ISysUserService userService = new SysUserServiceImpl();
				userService.registerSysUser(sysUser);
				
				response.sendRedirect(request.getContextPath() + "/view/login_admin.jsp");
				
			}
		}else if ("loginIn".equals(type)) {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			if (userName != null && !"".equals(userName) && password != null && !"".equals(password)) {
				ISysUserService userService = new SysUserServiceImpl();
				SysUserDomain sysUser = userService.querySysUser(userName, MD5Util.MD5(password));
				if (sysUser != null) {
					// 登录成功后将用户的整个登录信息存到session里去
					HttpSession session = request.getSession();
					session.setAttribute(HospitalConstants.USER_CODE_SESSION_KEY, sysUser.getUserCode());
					session.setAttribute(HospitalConstants.USER_NAME_SESSION_KEY, sysUser.getUserName());
					session.setAttribute(HospitalConstants.REAL_NAME_SESSION_KEY, sysUser.getRealName());
					// 登录成功后转发到reservation_form页面
					// 样式控制
					if(sysUser.getUserType()==1) {
						request.setAttribute("title", "预约单");
						request.setAttribute("activeCss", "yuyue");
						// request.getRequestDispatcher("/view/reservation_form.jsp").forward(request,
						// response);
						System.out.println("登录成功！");
						response.sendRedirect(request.getContextPath() + "/orderInfoServlet.do");
					}else {
						response.sendRedirect(request.getContextPath() + "/UserHopsitalServlet.do");
					}
				} else {
					// 重定向
					// request.getContextPath():就是hospital这个项目目录
					response.sendRedirect(request.getContextPath() + "/view/login_admin.jsp");
					System.out.println("登录失败！");
				}
			}
		} else if ("editorQuery".equals(type)) {
			// 样式控制
			request.setAttribute("title", "修改个人信息");
			request.getRequestDispatcher("/view/user_edit.jsp").forward(request, response);
		} else {
			// 将session对象保存的user对象销毁
			System.out.println("登录失效！");
			request.getSession().invalidate();// 让session对象无效
			request.getRequestDispatcher("/view/login_admin.jsp").forward(request, response);
		}
	}

//	public static void main(String[] args) {
//		ISysUserService userService = new SysUserServiceImpl();
//		Boolean flag = userService.loginCheck("admin", MD5Util.MD5("123456"));
//		System.out.println(flag);
//	}

}
