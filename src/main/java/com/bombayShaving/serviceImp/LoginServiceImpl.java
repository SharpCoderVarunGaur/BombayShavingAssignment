package com.bombayShaving.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombayShaving.entites.Login;
import com.bombayShaving.repositroy.LoginRepo;
import com.bombayShaving.service.LoginService;



@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepo loginRepo;

	@Override
	public Login createLogin(Login login) {
		Login l = this.loginRepo.save(login);
		return l;
	}

	@Override
	public List<Login> getLogin() {
		List<Login> l = this.loginRepo.findAll();
		return l;
	}

}
