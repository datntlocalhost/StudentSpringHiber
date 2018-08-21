package com.runsystem.datnt.services.implementations;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runsystem.datnt.daos.interfaces.PasswordResetDao;
import com.runsystem.datnt.daos.interfaces.UserDao;
import com.runsystem.datnt.entities.PasswordReset;
import com.runsystem.datnt.entities.User;
import com.runsystem.datnt.services.interfaces.PasswordResetService;

@Service
@Transactional
public class PasswordResetSeviceImpl implements PasswordResetService {
	
	@Autowired
	private PasswordResetDao resetDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<PasswordReset> selectProcessed() {
		return resetDao.selectProcessed();
	}

	public List<PasswordReset> selectUnprocess() {
		return resetDao.selectUnprocess();
	}

	public boolean insert(PasswordReset info) {
		
		User user = userDao.selectByUsername(info.getUsername());
		PasswordReset hasRequire = resetDao.hasRequire(info.getUsername());
		
		if (user != null && hasRequire == null) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			info.setIsProcess(0);
			info.setTime(df.format(calendar.getTime()));
			info.setStudentCode(info.getUsername());
			info.setNewPassword(passwordEncoder.encode(info.getNewPassword()));
			info.setStudentName(user.getStudent().getStudentName());
			resetDao.add(info);
			return true;
		}
		return false;
	}

	public PasswordReset acceptReset(String studentCode) {
		PasswordReset passwordReset = resetDao.hasRequire(studentCode);
		if (passwordReset != null) {
			User user = userDao.selectByUsername(studentCode);
			passwordReset.setIsProcess(1);
			user.setPassword(passwordReset.getNewPassword());
			userDao.update(user);
			resetDao.update(passwordReset);
			return passwordReset;
		}
		return null;
	}
}
