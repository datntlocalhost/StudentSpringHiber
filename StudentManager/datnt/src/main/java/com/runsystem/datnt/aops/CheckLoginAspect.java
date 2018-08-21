/**
 * CheckLoginAspect class
 * 
 * Define methods to execute check login
 */
package com.runsystem.datnt.aops;

import org.aspectj.lang.JoinPoint;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

public class CheckLoginAspect {
	
	/*
	 * AOP for check login, if user didn't login , will redirect to login page.
	 * 
	 * @param joinPoint
	 */
	public void onCheck(JoinPoint joinPoint) throws Throwable {
		//Get arguments in the method
		Object[] args = joinPoint.getArgs();
		
		for (Object obj : args) {
			//if obj is a instance of HttpServletRequest object then asignment it to request varible
			if (obj instanceof Model) {
				Model model = (Model) obj;
				model.addAttribute("user", getPrincipal());
				model.addAttribute("role", getRole());
 			}
		}
	}
	
	private String getPrincipal(){
        String userName = null;
        String role     = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
            role = ((UserDetails)principal).getAuthorities().iterator().next().getAuthority();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	
	private String getRole() {
        String role     = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            role = ((UserDetails)principal).getAuthorities().iterator().next().getAuthority();
        } else {
        	role = principal.toString();
        }
        return role;
	}
}
