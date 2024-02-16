package com.bombayShaving.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bombayShaving.entites.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer>{
   
	Employee findByEmail(String email);
}
