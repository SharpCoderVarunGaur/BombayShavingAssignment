package com.bombayShaving.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombayShaving.entites.Role;
import com.bombayShaving.repositroy.RoleRepo;
import com.bombayShaving.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {
 
	@Autowired
	private RoleRepo repo;
	@Override
	public Role createRole(Role role) {
		Role r=this.repo.save(role);
		return r;
	}

	@Override
	public Role getRoleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllRole() {
		List<Role> r=this.repo.findAll();
		return r;
	}

}
