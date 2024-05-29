package controller;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.net.URI;
import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.apache.commons.lang3.RandomStringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.DAOFactory;
import dao.IUrl;
import entity.Url;



@WebServlet(name ="UrlController" , urlPatterns = "/shorten-url")
public class UrlController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();
	IUrl urlDao = DAOFactory.createIurl();
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setStatus(201); //created
		StringBuffer sb = new StringBuffer();
		BufferedReader br = request.getReader();
		String attribute = null;
		
		
		JsonObject jsonObject;//obter o json do corpo da requisiçaop
		
		
		try(BufferedReader bf = request.getReader()){
			jsonObject = JsonParser.parseReader(bf).getAsJsonObject();
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String id;
		String url = jsonObject.get("url").getAsString();
		
		
		
		java.sql.Date expiresDate = java.sql.Date.valueOf(java.time.LocalDate.now());
		
		do {
			id = RandomStringUtils.randomAlphanumeric(5, 10);
		}while(urlDao.existById(id));
		
		
		var redirectedUrl = request.getRequestURL().toString().replace("shorten-url",id);

		Url newUrl = new Url(id,url,expiresDate);
		urlDao.saveUrl(newUrl);
		
		
		PrintWriter pw = response.getWriter();
		pw.print(gson.toJson(redirectedUrl));//response is a object of type product
	}
	

}
