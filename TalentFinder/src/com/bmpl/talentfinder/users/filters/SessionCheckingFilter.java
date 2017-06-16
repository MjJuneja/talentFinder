package com.bmpl.talentfinder.users.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionCheckingFilter
 */
@WebFilter("*.talent")
public class SessionCheckingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionCheckingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//FilterChain tells where to take request forward
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		//downcast Servlet request to http servlet request to get .getSession method
		HttpSession session = ((HttpServletRequest)request).getSession(false); //dont create new session and catch already made session
		if(session == null){
			((HttpServletResponse)response).sendRedirect("index.html");
		}
		else{
		  chain.doFilter(request, response); //doFilter chain takes request forward
	    }
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
