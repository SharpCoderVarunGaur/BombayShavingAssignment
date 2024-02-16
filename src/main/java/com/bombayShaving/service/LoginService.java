package com.bombayShaving.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bombayShaving.entites.Login;



@Component
public interface LoginService {
	
	public Login createLogin(Login login);
	
	public List<Login> getLogin();

}
