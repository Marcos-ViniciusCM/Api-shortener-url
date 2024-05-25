package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOFactory;
import dao.IUrl;
import entity.Url;


@WebServlet(name = "RedirectedUrl" , urlPatterns ="/*")
public class RedirectUrl extends HttpServlet {
	IUrl urlDao = DAOFactory.createIurl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		String id = "";
		System.out.println("caminho"+path);
		
		if(path != null && !path.isEmpty()) {
			id = path.substring(1);
		}
		
		Url url = urlDao.getById(id);
	
		 System.out.println("URL completa recuperada do banco de dados: " + url.getFullUrl());
		
		
		System.out.println("full url"+url.getFullUrl());
		
		
		response.sendRedirect(url.getFullUrl());
		
	}
}
