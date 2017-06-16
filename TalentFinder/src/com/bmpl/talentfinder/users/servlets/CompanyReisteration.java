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

/**
 * Servlet implementation class CompanyReisteration
 */
@WebServlet("/compreg")
public class CompanyReisteration extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userid = request.getParameter("userid");
		String password = request.getParameter("pwd");
		String compName = request.getParameter("companyname");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String Size = request.getParameter("size");
		
		CompanyDAO companydao = new CompanyDAO();
		
		PrintWriter out = response.getWriter();
		try{
		if(companydao.addCompany(userid, password, compName, phone, Size, name)){
		out.println("Register done!!");	
		}
		else{
			out.println("Register not done!!");	
		}
		
		}
		catch (Exception e) {
			out.println("Register fail..!"+e);	
		}
		response.setHeader("Cache-control", "no cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");  // page is never stored in cache
		
	}

}
