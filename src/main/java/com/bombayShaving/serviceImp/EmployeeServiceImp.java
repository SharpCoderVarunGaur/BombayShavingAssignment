package com.bombayShaving.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombayShaving.entites.Employee;
import com.bombayShaving.repositroy.EmployeeRepo;
import com.bombayShaving.service.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService {
 
	
	@Autowired
	 private EmployeeRepo employeeRepo;
	
	@Override
	public Employee createEmp(Employee emp) {
		Employee e=this.employeeRepo.save(emp);
		return e;
	}

	@Override
	public Employee getByIdEmployee(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> e=this.employeeRepo.findAll();	
		return e;
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return null;
	}

}
