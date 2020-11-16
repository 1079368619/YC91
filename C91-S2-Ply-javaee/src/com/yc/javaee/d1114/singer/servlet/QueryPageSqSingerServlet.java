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

@WebServlet("/QueryPageSqSingerServlet")
public class QueryPageSqSingerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SqSingerDao ssdao = new SqSingerDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		List<SqSinger> list = ssdao.selectPage();
		//
		String json = new Gson().toJson(list);
		System.out.println(json);
		response.getWriter().append(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
