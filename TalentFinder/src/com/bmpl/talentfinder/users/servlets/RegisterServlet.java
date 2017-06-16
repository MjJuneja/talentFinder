package com.bmpl.talentfinder.users.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmpl.talentfinder.users.dao.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String password = request.getParameter("pwd");
		UserDAO userDAO = new UserDAO();
		
		PrintWriter out = response.getWriter();
		try{
		if(userDAO.addUser(userid, password)){
		out.println("Register done!!");	
		}
		else{
			out.println("Register not done!!");	
		}
		
		}
		catch (Exception e) {
			out.println("Register fail..!"+e);	
		}
	}

}
