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
			// �쳣��־������Ա��
			e.printStackTrace();
			out.print("<h2>��������ִ��󣡣�</h2>");
		} catch (Exception e) {
			// �쳣��־������Ա��
			e.printStackTrace();
			out.print("<h2>ϵͳ��æ�����Ժ����ԣ���</h2>");
		}
	}

}
