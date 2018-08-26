package com.runsystem.datnt.logins;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runsystem.datnt.daos.interfaces.TokenDao;
import com.runsystem.datnt.daos.interfaces.UserDao;
import com.runsystem.datnt.entities.Token;
import com.runsystem.datnt.entities.User;
import com.runsystem.datnt.services.implementations.MyUserDetailsService;
import com.runsystem.datnt.utils.GenerateToken;
import com.runsystem.datnt.utils.Sha256Hash;
import com.runsystem.datnt.validations.UserValidator;

@Controller
public class LoginController {
	
	@Autowired
	TokenDao tokenDao;
	
	@Autowired
	MyUserDetailsService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String pageLoad(Model model, HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(@ModelAttribute User user, Model model, HttpServletRequest request, BindingResult bindingResult) {
		
		HttpSession session = request.getSession();
		
		UserValidator validator = new UserValidator();
		validator.validate(user, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "login";
		}
	
		UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
		
		if (userDetails != null) {
			if (userDetails.getPassword().equals(Sha256Hash.hash(user.getPassword()))) {
				session.setAttribute("userDetails", userDetails);
				session.setMaxInactiveInterval(15*60);
				Token oldToken = tokenDao.selectByUsername(user.getUsername());
				
				if (oldToken != null) {
					tokenDao.delete(oldToken);
				}
				
				Token newToken = GenerateToken.generate(user.getUsername(), 15);
				tokenDao.insert(newToken);
				session.setAttribute("userToken", newToken.getToken());
				
				return "redirect:/home";
			}
		}
		
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
