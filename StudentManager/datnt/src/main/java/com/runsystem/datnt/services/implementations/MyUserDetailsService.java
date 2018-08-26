package com.runsystem.datnt.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.runsystem.datnt.entities.Role;
import com.runsystem.datnt.services.interfaces.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.runsystem.datnt.entities.User user = userService.selectByUsername(username);
		
		if (user == null) {
			return null;
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		
		boolean enabled               = true;
		boolean accountNonExpired     = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked      = true;
		
		User springUser = new User(username, user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		return springUser;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}