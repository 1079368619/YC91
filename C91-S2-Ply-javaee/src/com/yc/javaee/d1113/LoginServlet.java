package com.yc.javaee.d1113;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/1113/简单的企业MIS/login.s")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		String account = request.getParameter("account");
		//
		String pwd = request.getParameter("pwd");
		String rvcode = request.getParameter("vcode");
		String svcode = (String) request.getSession().getAttribute("vcode");
		if(svcode.equalsIgnoreCase(rvcode) == false) {
			response.getWriter().append("-1");
		}
		if("zhangsan".equals(account) && "123".equals(pwd)) {
			//
			HttpSession session = request.getSession();
			//
			session.setAttribute("loginedAccount", account);
			//
			Cookie cookie = new Cookie("loginedAccount", account);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			response.getWriter().append("1");
		}else {
			//
			response.getWriter().append("0");
		}
		
	}

}
