package com.yc.javaee.d1114;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		HttpSession session = request.getSession();
		//
		String vcode = VerifyCodeUtils.outputImage(response);
		//
		session.setAttribute("vcode", vcode);
		//
		session.setAttribute("vtime", new Date());
		//session.setMaxInactiveInterval(60);
		
		
	}


}
