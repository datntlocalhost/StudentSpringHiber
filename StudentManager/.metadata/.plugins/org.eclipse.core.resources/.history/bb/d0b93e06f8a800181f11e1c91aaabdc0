package com.runsystem.datnt.aops;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.security.core.userdetails.UserDetails;

import com.runsystem.datnt.daos.interfaces.TokenDao;
import com.runsystem.datnt.entities.Token;

public class CheckTokenAOP {
	
	private TokenDao tokenDao;
	
	public void onCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		Object[] agrs = joinPoint.getArgs();
		HttpServletRequest req = null;
		HttpServletResponse res = null;
		HttpSession session = null;
		
		for (Object obj : agrs) {
			if (obj instanceof HttpServletRequest) {
				req = (HttpServletRequest) obj;
			}
			if (obj instanceof HttpServletResponse) {
				res = (HttpServletResponse) obj;
			}
		}
		
		session = req.getSession();
		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
		String userToken = (String) session.getAttribute("userToken");
		if (userToken != null && !userToken.isEmpty() && userDetails != null) {
			Token token = tokenDao.selectByUserSeries(userDetails.getUsername(), userToken); 
			
			if (token == null || token.getTimestamp().getTime() <= System.currentTimeMillis()) {
				res.sendRedirect("/datnt/login");
			}
		}
		
	}


}
