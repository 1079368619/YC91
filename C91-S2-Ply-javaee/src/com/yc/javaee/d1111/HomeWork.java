package com.yc.javaee.d1111;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeWork.s")
public class HomeWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String x = request.getParameter("x");
		String z = request.getParameter("z");
		String y = request.getParameter("y");
		try {
			double a = Double.parseDouble(x), b = Double.parseDouble(y);
			double c = 0;
			switch (z) {
			case "+":
				c = a + b;
				break;
			case "-":
				c = a - b;
				break;
			case "*":
				c = a * b;
				break;
			case "/":
				c = a / b;
				break;
			}
			out.print("<h2>" + x + z + y + " = " + c + " </h2>");
		} catch (NumberFormatException e) {
			// 异常日志给程序员看
			e.printStackTrace();
			out.print("<h2>输入的数字错误！！</h2>");
		} catch (Exception e) {
			// 异常日志给程序员看
			e.printStackTrace();
			out.print("<h2>系统繁忙，请稍后再试！！</h2>");
		}
	}

}
