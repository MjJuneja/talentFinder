package com.bmpl.talentfinder.users.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmpl.talentfinder.company.dao.CompanyDAO;
import com.bmpl.talentfinder.company.dto.CompanyDTO;

/**
 * Servlet implementation class CompanyServlet
 */
@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String password = request.getParameter("pwd");
		String compName = request.getParameter("comp");
		CompanyDTO companydto = new CompanyDTO();
		companydto.setUserid(userid);
		companydto.setPassword(password);
		companydto.setCompName(compName);
		CompanyDAO companydao = new CompanyDAO();
		
		try{
			if(companydao.isAuthenticate(companydto)){
				HttpSession session1 = request.getSession(true);
				session1.setAttribute("userid",userid);
				session1.setAttribute("company", compName);
				response.sendRedirect("WelcomeComp");
			}
			else{
				PrintWriter out = response.getWriter();
		        out.println("invalid userid or password");
		        out.close();
			}
		}
		catch (Exception e) {
			response.sendRedirect("customerror.html");
			e.printStackTrace();
			
		}
		
	}

}
