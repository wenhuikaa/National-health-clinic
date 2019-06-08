package com.hospital.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.BsAreaDomain;
import com.bs.model.BsCityDomain;
import com.hospital.service.IBsCityService;
import com.hospital.service.impl.BsCityServiceImpl;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class BsCityServlet
 */
@WebServlet("/bsCity.do")
public class BsCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//hospital_management.jsp������getType����
		//ͬһ��servletҪʵ�ֶ�����ܣ���Ҫ��һ����־getType
		String getType=req.getParameter("getType");
		
		JSONObject jsonObject = new JSONObject();
		if("city".equals(getType)) {
			//hospital_manahement.jsp������provinceCode����
			String provinceCode=req.getParameter("provinceCode");
			//System.out.println("�ɻ�ȡ��ʡ����:"+provinceCode);
			IBsCityService bsCityService=new BsCityServiceImpl();
			List<BsCityDomain> citys= bsCityService.queryCity(provinceCode);
			jsonObject.put("citys", citys);
		}else if("area".equals(getType)) {
			String cityCode = req.getParameter("cityCode");
			IBsCityService bsCityService = new BsCityServiceImpl();
			List<BsAreaDomain> areas = bsCityService.queryArea(cityCode);
			jsonObject.put("areas", areas);
		}
		
		//�����첽ˢ����Ӧ����Ϣ����
		resp.setContentType("application/json; charset=utf-8");
		//�õ���ӡ��
		PrintWriter writer=resp.getWriter();
		//System.out.println(jsonObject.toString());
		writer.print(jsonObject);
		writer.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
