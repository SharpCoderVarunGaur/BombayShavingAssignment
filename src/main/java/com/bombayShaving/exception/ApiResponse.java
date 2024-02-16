package com.bombayShaving.exception;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    
	
	private String mss;
	private Boolean success;
	public String getMss() {
		return mss;
	}
	public void setMss(String mss) {
		this.mss = mss;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public ApiResponse(String mss, Boolean success) {
		super();
		this.mss = mss;
		this.success = success;
	}
	public ApiResponse() {
		super();
	}
	
}
