package com.bombayShaving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bombayShaving.security.JwtHelper;
import com.bombayShaving.security.Response;
import com.bombayShaving.security.WtRequest;



@RestController
@RequestMapping("api/auth/login")
public class Token {
	@Autowired
	private UserDetailsService detailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper helper;
	
	
	@GetMapping
	public ResponseEntity<Response> createToekn(@RequestBody WtRequest  request ){
		
		try{
			System.out.println("Hello12012901");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
			System.out.println("Hello12012902");
		}catch(BadCredentialsException e) {
			System.out.println("Hello12012903");
			throw new BadCredentialsException("Invalid user name");
		}
		
		UserDetails userDetails=this.detailsService.loadUserByUsername(request.getUserName());
		String token =this.helper.generateToken(userDetails);
		System.out.println("Hello1201290");
		Response res=new Response();
		res.setToken(token);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		   
		
	}
}
