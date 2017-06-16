package com.bmpl.talentfinder.users.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bmpl.talentfinder.users.dao.UserDAO;
import com.bmpl.talentfinder.users.dto.UserRoleRightMappingDTO;
import com.bmpl.talentfinder.users.dto.userDTO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")

//inherited HttpServlet then only it can become servlet
/*
 * HttpServlet contains all http communication ready made methods
 * Class above httpServlet is GenericServlet which help us communicate with ftp and other means
 * GenericServlet has Protocol Base which we can override for different protocols
 * Above all these there is an interface called Servlet
 * inside Servlet interface there are three methods which are its life cycle
 *  1.)init()  : will be called when servlet is initialized. Will be called once
 *  2.)service() : 
 *  3.)destroy() : will be called when servlet is destroyer or closed or means server is down. Will be called once
 *  
 *  
 *  Inside httpServlet these 3 methods will be there as these will be implemented by the base class.
 *  and here service will be distributed in types like doGet and doPost.
 *  
 *  HttpServlet require 2 things httpServletRequest and httpServletResponse and these two are interface which Tomcat implement
 *  these two are made by server and their objects are kept in HttpServlet and it has "has a" relation.
 *  
 *  httpServletRequest and httpServletResponse has ServletRequest and ServletResponse as their parent interfaces 
 *  
 *  This is really heavy so its object is made only once.
 *  
 *  CGI was first language implementing this but as a single user came a process was made to run for every single request.
 *  where as in servlet object was made only once and thread will be made for the user. which will be keeping the reference of 
 *  that object. On making thread stack is created for them.
 *  
 *  only local variable will be made as instance variable if made will be common to every thread and ny effect by one will effect 
 *  for all.
 *  
 *  
 */


public class UserServlet extends HttpServlet {
	private int counter; //object will be made only once as it is an instance variable change in its value will be effected by all.
       
   @Override 
   public void init(){
	   counter =1;
	   System.out.println("Init Call once.....");
   }
   
   //doGet shall be made doPost for not showing data in web address
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userid = request.getParameter("userid"); //will work to get value from html
		String password = request.getParameter("pwd");
		String pinCodeData = request.getParameter("pincode");
		userDTO userDTO = new userDTO();
		userDTO.setUserid(userid);
		userDTO.setPassword(password);
		userDTO.setPinCode(pinCodeData);
		int price =1000;
		int quantity = 2;
		int bill = price * quantity;
		request.setAttribute("bill", bill); //stores bill in request
		String color = request.getParameter("color");
		UserDAO userDAO = new UserDAO();
//		userDAO.isAuthenticate(userDTO);  //give boolean
		int pinCode=1000;
//		if(userid!=null || password !=null)
//		if(userid.equals(password)){
		try{
			UserRoleRightMappingDTO userRoleDTO = userDAO.isAuthenticate(userDTO);
			if(userRoleDTO!=null){
				
			//Check cookie exist or not
			boolean isCookieFound = false;
			Cookie cookies[] = request.getCookies();
			if(cookies!=null && cookies.length>0){  //cookies!= null checks cookie is of our domain or not
			for(Cookie c: cookies){
				if(c.getName().equals("color")){  //checks cookie is for parameter we want
					isCookieFound=true;
					break;
				}
			}
			}
			//Define cookie	
			if(!isCookieFound){
			Cookie cookie = new Cookie("color",color);
			cookie.setMaxAge(24*60*60); //setting timeperiod of cookie to exist
			response.addCookie(cookie); //cookies is made on server and sent on response of request and then save on system
			}
			HttpSession session = request.getSession(true); //Create a New Session {20 digit by now and keep updating}
			session.setAttribute("userid", userid);  //on logging in a session key is generated and in it userid of user is saved
			session.setAttribute("pin", pinCode);
			//String enCodedURL = response.encodeRedirectURL("welcome.talent");
			//response.sendRedirect(enCodedURL);
			session.setAttribute("userinfo", userRoleDTO);
			RequestDispatcher rd = request.getRequestDispatcher("welcome.talent");
			rd.forward(request, response);
			
//			response.sendRedirect("welcome.talent");
			
			
			/*sendredirect take us from one page to another within and outside context but don't carry forward request
			*always create a new request
			*it always need a get
			*it is a client side forward
			*/
			
//			response.sendRedirect("welcome.html"); //html was static so we made it dynamic with servlet
//			response.sendRedirect("welcome?uid="+userid+"&pin="+pinCode); //sending multiple data via url
			//response.sendRedirect("welcome?uid="+userid);  //Query String method to send data in request
			
			//alternative of response and it carry request as well
			/*
			 * request dont have pincode so to add pincode in request we will do this
			 * redirect within the context and it carry same request and is a server side forward.
			 * client dont know forwarded url
			 */
//			request.setAttribute("pin", pinCode);
//			
//			RequestDispatcher rd = request.getRequestDispatcher("welcome");
//			rd.forward(request, response);
			
			/*
			 * sendRedirect can take us to such page which are not under our project
			 * ContectRoot :talent finder is our context root here.
			 */
		}
		else{
			PrintWriter out = response.getWriter();
	        out.println("invalid userid or password");
	        out.close();
		}
		}
		catch(SQLException e){
			//we can have multiple exceptions
			response.sendRedirect("customerror.html");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			response.sendRedirect("customerror.html");
			e.printStackTrace();
		}
		catch (Exception e) {
			response.sendRedirect("customerror.html");
			e.printStackTrace();
		}
		
//		PrintWriter out = response.getWriter();    //response.getWriter give indication that print on response
//		//printWriter is connected with response so any thing we write will go with response and it can be connected with any means like buffer file etc.
//        out.println("hello client "+counter);
//        counter++;
//        System.out.println("Get service call again and again,,,...");
//        out.close();
	
	}

}
