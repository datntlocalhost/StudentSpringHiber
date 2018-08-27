package com.runsystem.datnt.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Servlet Filter implementation class CustomFilter
 */
public class CustomFilter implements Filter {
    /**
     * Default constructor. 
     */
    public CustomFilter() {
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
		
		//Token token = tokenDao.selectByUsername("admin");
		
		HttpServletRequest  req     = (HttpServletRequest) request;
		HttpServletResponse res     = (HttpServletResponse) response;
		HttpSession         session = req.getSession();
		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
		
		List<String> authorities = new ArrayList<String>();
		
		String requestURI = req.getRequestURI();
		
		if (userDetails != null) {
			
			for (GrantedAuthority author : userDetails.getAuthorities()) {
				authorities.add(author.getAuthority());
			}
			
			if (requestURI.startsWith("/datnt/home")) {
				if (!authorities.contains("ROLE_ADMIN") && !authorities.contains("ROLE_STUDENT")) {
					res.sendRedirect("/datnt/403");
					return;
				}
			} else if (requestURI.startsWith("/datnt/student")) {
				if (!authorities.contains("ROLE_STUDENT")) {
					res.sendRedirect("/datnt/403");
					return;
				}
			} else if (requestURI.startsWith("/datnt/admin")) {
				if (!authorities.contains("ROLE_ADMIN")) {
					res.sendRedirect("/datnt/403");
					return;
				}
			} 
			chain.doFilter(request, response);
		} else {
			if (requestURI.startsWith("/datnt/login")) {
				chain.doFilter(request, response);
				return;
			}
			res.sendRedirect("/datnt/login");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
