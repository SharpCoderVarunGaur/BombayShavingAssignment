package com.bombayShaving.security;

import org.springframework.stereotype.Component;

@Component
public class WtRequest {

	String userName;
	String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public WtRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public WtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WtRequest [userName=" + userName + ", password=" + password + ", getUserName()=" + getUserName()
				+ ", getPassword()=" + getPassword() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
