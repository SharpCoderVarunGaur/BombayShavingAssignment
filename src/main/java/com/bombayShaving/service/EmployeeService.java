package com.bombayShaving.service;

import java.util.List;

import com.bombayShaving.entites.Employee;

public interface EmployeeService {
 
	
	Employee createEmp(Employee emp);
	
	Employee getByIdEmployee(int id);
	
	List<Employee> getAllEmployee();
	
	Employee updateEmployee(Employee emp);
	
}
