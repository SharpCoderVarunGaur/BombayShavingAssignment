package com.bombayShaving.service;

import java.util.List;

import com.bombayShaving.entites.Role;

public interface RoleService  {
  
	Role createRole(Role role);
	
	Role getRoleById(int id);
	
	List<Role> getAllRole();
}
