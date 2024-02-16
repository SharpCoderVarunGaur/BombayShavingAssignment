package com.bombayShaving.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bombayShaving.entites.Employee;
import com.bombayShaving.entites.Role;
import com.bombayShaving.exception.ApiResponse;
import com.bombayShaving.serviceImp.EmployeeServiceImp;
import com.bombayShaving.serviceImp.RoleServiceImp;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/employees")
public class EmployeeController {
	
	private final Logger looger=LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeServiceImp employeeServiceImp;
	
	@Autowired
	private RoleServiceImp roleServiceImp;
	 
	@PostMapping
	public ResponseEntity<?> createEmp(@Valid @RequestBody Employee emp,BindingResult bindingResult){
		   System.out.println(emp.getName());
		   if(bindingResult.hasErrors()) {
				return new ResponseEntity<ApiResponse>(new ApiResponse("Validation Errors"+bindingResult.getAllErrors(),false),HttpStatus.CONFLICT);
			}
		  looger.info("this is payload of create user:{}",emp);
   
		 Employee e= this.employeeServiceImp.createEmp(emp);
		
		return new ResponseEntity<Employee>(e,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployee(){
		System.out.println("Hello admin");
		List<Employee> e=this.employeeServiceImp.getAllEmployee();
		return ResponseEntity.ok(e);
	}
	
	@PostMapping("role")
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		Role r=this.roleServiceImp.createRole(role);
		return ResponseEntity.status(HttpStatus.CREATED).body(r);
	}
	
	
	@GetMapping("role/all")
	public ResponseEntity<List<Role>> getAllRole(){
		List<Role> e=this.roleServiceImp.getAllRole();
		return ResponseEntity.ok(e);
		
	}

}
