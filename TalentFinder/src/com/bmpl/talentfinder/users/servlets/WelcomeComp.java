package com.bmpl.talentfinder.users.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmpl.talentfinder.company.dao.CompanyDAO;
import com.bmpl.talentfinder.company.dto.PostDTO;

/**
 * Servlet implementation class WelcomeComp
 */
@WebServlet("/WelcomeComp")
public class WelcomeComp extends HttpServlet {
	String postBox="";
	void createPost(String compName,String JobPost){
		 postBox = "<div style='border:1px solid red'>"
					+ "<div id='compname' style='border:1px dotted black'>"+compName+""
							+ "</div><div id='Content' style='border:1px solid blue'>"+JobPost+""
									+ "</div></div><br>";
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session1 = request.getSession(false);
			
			if(session1==null){
				response.sendRedirect("complogin.html");
			}
			else{
				ArrayList<PostDTO> list=null;
				CompanyDAO companydao = new CompanyDAO();
				PrintWriter out = response.getWriter();
				PostDTO postdto = new PostDTO();
				String userid = (String)session1.getAttribute("userid");
				String logout = "<form action='logout' method='get'><button type='submit'>Logout</button></form>";
				String post = "<form action='WelcomeComp' method='get'><textarea name='jobpost' cols='30' rows='10'>"
						+ "</textarea><br><button type='submit'>Submit</button></form>";
				out.println("<html><body><h1>Welcome "+userid+"</h1><br>"+logout+"<br><br>"+post);
				LocalTime hour = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
				String JobPost = request.getParameter("jobpost");
				String compName = (String)session1.getAttribute("company");
				postdto.setCompanyName(compName);
				postdto.setPost(JobPost);
				postdto.setTime(hour);
				postdto.setStar("1");
				postdto.setEmail(userid);
				try {
					if(JobPost!=null){
					if(companydao.addPost(postdto)){
						list=companydao.collectPost();
						
					for(PostDTO job:list){
						createPost(job.getCompanyName(), job.getPost());
						out.println("<br><br>"+postBox+" "+postdto.getTime()+"<br><br>");
					}

					out.println("</body></html>");
					response.setHeader("Cache-control", "no cache, no-store");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Expires", "-1"); 
					out.close();
}
					}
				} catch (ClassNotFoundException e) {
					response.sendRedirect("error.html");
					e.printStackTrace();
				} catch (SQLException e) {
					response.sendRedirect("error.html");
					e.printStackTrace();
				}
	}
	}
}
