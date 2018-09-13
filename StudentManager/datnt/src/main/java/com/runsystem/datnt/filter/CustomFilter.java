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

import com.runsystem.datnt.dtos.RoleDto;
import com.runsystem.datnt.dtos.UserDto;

/**
 * Servlet Filter implementation class CustomFilter
 */
//@Component
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
		
		HttpServletRequest  req     = (HttpServletRequest) request;
		HttpServletResponse res     = (HttpServletResponse) response;
		HttpSession         session = req.getSession();
		UserDto userDetails = (UserDto) session.getAttribute("user");
		
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		if (userDetails != null) {
			
			List<String> authorities = new ArrayList<String>();
			
			for (RoleDto role : userDetails.getRoles()) {
				authorities.add(role.getRoleName());
			}
			
			if (requestURI.startsWith(contextPath + "/home")) {
				if (!authorities.contains("ROLE_ADMIN") && !authorities.contains("ROLE_STUDENT")) {
					res.sendRedirect(contextPath + "/403");
					return;
				}
			} else if (requestURI.startsWith(contextPath + "/student")) {
				if (!authorities.contains("ROLE_STUDENT")) {
					res.sendRedirect(contextPath + "/403");
					return;
				}
			} else if (requestURI.startsWith(contextPath + "/admin")) {
				if (!authorities.contains("ROLE_ADMIN")) {
					res.sendRedirect(contextPath + "/403");
					return;
				}
			} 
			chain.doFilter(request, response);
		} else {
			if (requestURI.startsWith(contextPath + "/login")) {
				chain.doFilter(request, response);
				return;
			}
			res.sendRedirect(contextPath + "/login");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
