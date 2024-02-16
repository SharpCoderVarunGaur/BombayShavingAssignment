package com.bombayShaving.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bombayShaving.entites.Login;
import com.bombayShaving.entites.Role;
import com.bombayShaving.exception.ApiResponse;
import com.bombayShaving.repositroy.LoginRepo;
import com.bombayShaving.repositroy.RoleRepo;
import com.bombayShaving.serviceImp.LoginServiceImpl;

import jakarta.validation.Valid;




@RestController
@RequestMapping("Sign-up")
public class LoginController {

	@Autowired
	private LoginServiceImpl impl;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo repo;
	
	@Autowired
	private LoginRepo loginRepo;
	
	
	@PostMapping
	public ResponseEntity<?> createLogin(@RequestBody Login login){
		Login login2=this.loginRepo.findByUserName(login.getUserName());
		System.out.println("Hello123" +login.getRole().isEmpty() );
		if(login2!=null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("UserName is already Exist",false),HttpStatus.BAD_REQUEST);
		}
		if( login.getRole().isEmpty() || login.getPassword()==null||login.getUserName()==null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("Please enter"+ (login.getRole().isEmpty() ?" roleId": login.getPassword() ==null ? " password":login.getUserName()==null ? " userName":"") +" in payload",false),HttpStatus.BAD_REQUEST);
		}
		System.out.println("Hello123" + login.getUserName());
		System.out.println("pas"+login.getPassword());
		String password=this.encoder.encode(login.getPassword());
		System.out.println("pas"+password);
		
        List<Role> l1=login.getRole().stream().map(e-> {
		System.out.println("Hello1243" + e.getId());
			Optional<Role> r = this.repo.findById(e.getId());	
			Role r1 = r.get();
			return r1;
			        	
		}).collect(Collectors.toList());
        System.out.println("Hello1243" + l1);
        login.setRole(l1);
		login.setPassword(password);
		Login l=this.impl.createLogin(login);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(l);
	}
	
	
//	@GetMapping("/get")
//	public ResponseEntity<List<Login>> getAllLogin(){
//		List<Login> l=this.impl.getLogin();
//	return ResponseEntity.ok(l);
//	}
//	
	
}
