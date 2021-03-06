package com.yc.javaee.d1114.singer.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.javaee.d1114.singer.bean.SqSinger;
import com.yc.javaee.d1114.singer.dao.SqSingerDao;

@WebServlet("/ssQueryPage.s")
public class QueryPageSqSingerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SqSingerDao ssdao = new SqSingerDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String page = request.getParameter("page");
		String pnumber = request.getParameter("pnumber");
		int ipage = page == null ? 1 : Integer.parseInt(page);
		int ipnumber = pnumber == null ? 10 : Integer.parseInt(pnumber);
		List<SqSinger> list = ssdao.selectPage(ipage, ipnumber);
		//
		String json = new Gson().toJson(list);
		System.out.println(json);
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
