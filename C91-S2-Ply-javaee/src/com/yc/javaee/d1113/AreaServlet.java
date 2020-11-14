package com.yc.javaee.d1113;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/1111/AreaServlet")
public class AreaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求对象的字符集 ==》post
		request.setCharacterEncoding("UTF-8");
		// 设置响应对象的字符集
		response.setCharacterEncoding("UTF-8");
		// 设置网页的字符集
		response.setContentType("text/html;charset=utf-8");
		String level = request.getParameter("level");
		if ("0".equals(level)) {
			response.getWriter().append(Arrays.toString(AreaConstants.PROVINCES));
		} else if ("1".equals(level)) {
			String shengIndex = request.getParameter("shengIndex");
			int index = Integer.parseInt(shengIndex);
			response.getWriter().append(Arrays.toString(AreaConstants.CITYS[index]));
		} else {
			String shiIndex = request.getParameter("shiIndex");
			int index = Integer.parseInt(shiIndex);
			response.getWriter().append(Arrays.toString(AreaConstants.COUNTYS[0][0]));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
