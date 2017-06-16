package com.bmpl.talentfinder.users.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout.talent")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
//		if(session==null){
//			response.sendRedirect("index.html"); //on directly accessing this page it will throw us to index.html
//		}
//		else{
		
		String colorValue="";
		Cookie cookies[] = request.getCookies();
		if(cookies!=null && cookies.length>0){  //cookies!= null checks cookie is of our domain or not
		for(Cookie c: cookies){
			if(c.getName().equals("color")){  //checks cookie is for parameter we want
				colorValue=c.getValue();
				break;
			}
		}
		}
		
			session.removeAttribute("userid");
			session.removeAttribute("pin");
			session.invalidate();  //it kills the session
//			RequestDispatcher rd = request.getRequestDispatcher("header");
//			rd.include(request, response);
			out.println("<html><body bgcolor='"+colorValue+"'>");
			out.println("Logout Successfully...");
			RequestDispatcher rd2 = request.getRequestDispatcher("footer");
			rd2.include(request, response);
			response.setHeader("Cache-control", "no cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");
			
	//	}
	}

}
