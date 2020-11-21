package com.yc.javaee.d1118;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

@WebServlet("/upload.s")
@MultipartConfig(maxFileSize=1024*1024*10)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		request.setCharacterEncoding("utf-8");
		//
		response.setCharacterEncoding("utf-8");
		//
		response.setContentType("text/html;charset=utf-8");
		Part part = request.getPart("myfile");//
		part.getSubmittedFileName();//
		part.getSize();
		//
		String path = request.getServletContext().getRealPath("/headimages/");
		System.out.println(path);
		path += part.getSubmittedFileName();
		part.write(path);
		
		Gson gson = new Gson();
		Map<String, String> data = new HashMap<>();
		data.put("msg", "file upload success");
		data.put("path", "/headimages/" + part.getSubmittedFileName());
		response.getWriter().append(gson.toJson(data));
	}

}
