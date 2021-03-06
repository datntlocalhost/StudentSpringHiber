package com.runsystem.datnt.services.impl;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.TokenDao;
import com.runsystem.datnt.dtos.TokenDto;
import com.runsystem.datnt.dtos.UserDto;
import com.runsystem.datnt.exceptions.AuthException;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.services.TokenService;
import com.runsystem.datnt.utils.GenerateToken;
import com.runsystem.datnt.utils.LogginUtils;

@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private TokenDao tokenDao;

	/*
	 * Create new token and insert this token into database
	 * 
	 * @param username
	 * 
	 * @return true if create and insert success, else return false.
	 */
	@Transactional
	public TokenDto createToken(String username) {
		LogginUtils.getInstance().logStart(this.getClass(), "createToken");
		boolean success = true;
		
		//Create new token with username
		TokenDto token = GenerateToken.generate(username, 15);
		
		if (token == null) {
			LogginUtils.getInstance().logEnd(this.getClass(), "createToken");
			return null;
		}
		
		try {
			//Call insert method to insert token to database
			tokenDao.insert(token);
			
		} catch (InsertException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		} catch (IOException e) {
			success = false;
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		

		LogginUtils.getInstance().logEnd(this.getClass(), "createToken");
		
		if (success) {
			return token;
		} else {
			return null;
		}
	}

	/*
	 * Get last token of user has <username>.
	 * 
	 * @param username
	 * 
	 * @return tokendto
	 */
	@Transactional
	public TokenDto getLastToken(String username) {
		LogginUtils.getInstance().logStart(this.getClass(), "getLastToken");
		TokenDto token = null;

		try {
			
			token = tokenDao.selectLastToken(username);
			
		} catch (IOException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getLastToken");
		return token;
	}

	/*
	 * Check token is valid, throws AuthException if token is invalid
	 * 
	 * @param session
	 * 
	 * @throws AuthException
	 */
	@Transactional
	public void checkValidToken(HttpSession session) throws AuthException {
		LogginUtils.getInstance().logStart(this.getClass(), "checkValidToken");
		
		UserDto user = (UserDto) session.getAttribute("user");
		TokenDto token = (TokenDto) session.getAttribute("token");
		
		if (user == null || token == null) {
			LogginUtils.getInstance().logEnd(this.getClass(), "checkValidToken");
			throw new AuthException("AuthException: ");
		}
		
		TokenDto lastToken = getLastToken(user.getUsername());
		
		if (lastToken != null) {
			boolean isSameToken = token.getToken().equals(lastToken.getToken());
			boolean hasExpired = lastToken.getTimestamp().getTime() < System.currentTimeMillis();
			
			if (!isSameToken || hasExpired) {
				LogginUtils.getInstance().logEnd(this.getClass(), "checkValidToken");
				throw new AuthException("AuthException: ");
			}
			
		} else {
			LogginUtils.getInstance().logEnd(this.getClass(), "checkValidToken");
			throw new AuthException("AuthException: ");
		}
		LogginUtils.getInstance().logEnd(this.getClass(), "checkValidToken");
	}

}
