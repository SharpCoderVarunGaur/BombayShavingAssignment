package com.bombayShaving.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.bombayShaving.entites.Employee;
import com.bombayShaving.entites.Login;






@Component
public class CustomUserDetail implements UserDetails {
	
	
@Autowired	 
private Login login;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.login.getRole().stream().map(role-> new SimpleGrantedAuthority(role.getEmpRole())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return login.getPassword();
	}

	@Override
	public String getUsername() {
		return login.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public CustomUserDetail(Login login) {
		super();
		this.login = login;
	}

	public CustomUserDetail() {
		super();
	}
	




}
