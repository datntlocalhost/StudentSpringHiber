package com.runsystem.datnt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.TokenDao;
import com.runsystem.datnt.dtos.TokenDto;
import com.runsystem.datnt.exceptions.InsertException;
import com.runsystem.datnt.exceptions.SelectNullException;
import com.runsystem.datnt.services.TokenService;
import com.runsystem.datnt.utils.GenerateToken;
import com.runsystem.datnt.utils.LogginUtils;

@Service
@Transactional
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
	public TokenDto getLastToken(String username) {
		LogginUtils.getInstance().logStart(this.getClass(), "getLastToken");
		TokenDto token = null;

		try {
			
			token = tokenDao.selectLastToken(username);
			
		} catch (SelectNullException e) {
			LogginUtils.getInstance().logError(this.getClass(), e);
		}
		
		LogginUtils.getInstance().logEnd(this.getClass(), "getLastToken");
		return token;
	}

}