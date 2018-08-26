/**
 * CheckLoginAspect class
 * 
 * Define methods to execute check login
 */
package com.runsystem.datnt.aops;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import com.runsystem.datnt.daos.interfaces.TokenDao;
import com.runsystem.datnt.entities.Token;

public class SetRoleAOP {
	
	private TokenDao tokenDao;
	
	/*
	 * AOP for check login, if user didn't login , will redirect to login page.
	 * 
	 * @param joinPoint
	 */
	public void onSet(JoinPoint joinPoint) throws Throwable {		
		//Get arguments in the method
		Object[] args = joinPoint.getArgs();
		
		HttpServletRequest  req = null;
		HttpServletResponse res = null;
		HttpSession         session = null;
		Model model = null;
		UserDetails userDetails = null;
		
		for (Object obj : args) {
			//if obj is a instance of HttpServletRequest object then asignment it to request varible
			if (obj instanceof HttpServletRequest) {
				req = (HttpServletRequest) obj;
 			}
			
			if (obj instanceof HttpServletResponse) {
				res = (HttpServletResponse) obj;
			}
			
			if (obj instanceof Model) {
				model = (Model) obj;
			}
		}
		
		
		session = req.getSession();
		userDetails = (UserDetails) session.getAttribute("userDetails");
		String userToken = (String) session.getAttribute("userToken");
		if (userToken != null && !userToken.isEmpty() && userDetails != null) {
			Token token = tokenDao.selectByUserSeries(userDetails.getUsername(), userToken); 
			
			if (token != null && token.getTimestamp().getTime() > System.currentTimeMillis()) {
				model.addAttribute("user", userDetails.getUsername());
				model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority());
				return;
			} 	                     
		} 
		res.sendRedirect("/datnt/login");
	}
	
	public TokenDao getTokenDao() {
		return tokenDao;
	}

	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}
	/*
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
            
        } else {
            userName = principal.toString();
        }
        return userName;
    }*/
	
	
}