package com.bombayShaving.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bombayShaving.entites.Employee;
import com.bombayShaving.entites.Login;
import com.bombayShaving.repositroy.EmployeeRepo;
import com.bombayShaving.repositroy.LoginRepo;



@Component
public class UserDetailService implements UserDetailsService {

	@Autowired
	private LoginRepo loginRepo ;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login=this.loginRepo.findByUserName(username);
		
		if(login==null) {
			
		}
		CustomUserDetail c=new CustomUserDetail(login);
		System.out.println("Hello12012907");
		return c;
	}
 
}
