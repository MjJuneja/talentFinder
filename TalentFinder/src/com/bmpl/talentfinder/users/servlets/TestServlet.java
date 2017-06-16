package com.bmpl.talentfinder.users.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
//@WebServlet("/TestServlet")
//@WebServlet(urlPatterns="/TestServlet",loadOnStartup=1)
public class TestServlet extends HttpServlet {
	private ServletConfig sc;
	@Override
	public void init(ServletConfig sc) throws ServletException{
		super.init(sc);
		this.sc=sc;
		System.out.println("Load on Startup......");
		System.out.println("Servlet COnfig "+sc.getInitParameter("email"));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I will call when first Request Comes....");
		String phoneNo = this.getServletConfig().getServletContext().getInitParameter("tollfreeno");
		String email = this.getServletConfig().getInitParameter("email"); //but this is available in this servlet only not in any other
		/*
		 * first message need to be set by logging in and then only we can show it elsewhere
		 */
		String msg = (String)this.getServletConfig().getServletContext().getAttribute("message");
		response.getWriter().println("email is:"+email+" phone is:"+phoneNo + " Message is:"+msg);
	}

}
