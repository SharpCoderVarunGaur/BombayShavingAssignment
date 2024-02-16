package com.bombayShaving.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bombayShaving.entites.Employee;
import com.bombayShaving.entites.Login;
import com.bombayShaving.entites.Role;
import com.bombayShaving.exception.ApiResponse;
import com.bombayShaving.exception.EmailSameException;
import com.bombayShaving.repositroy.EmployeeRepo;
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
	
	   
	@Autowired
    private Employee empe;
	@Autowired
	private EmployeeRepo employeeRepo;
	 
	@PreAuthorize("hasRole('ADMIN') || hasRole('MANAGER')")
	@PostMapping
	public ResponseEntity<?> createEmp(@Valid @RequestBody Employee emp,BindingResult bindingResult){
			if(emp.getEmail()!=null) {
				empe= this.employeeRepo.findByEmail(emp.getEmail());
				if(empe !=null ) {
					return new ResponseEntity<ApiResponse>(new ApiResponse("Email is already Exist",false),HttpStatus.BAD_REQUEST);
	
					}
			}
				
	
		
		   if(bindingResult.hasErrors()) {
				return new ResponseEntity<ApiResponse>(new ApiResponse("Validation Errors"+bindingResult.getAllErrors(),false),HttpStatus.CONFLICT);
			}
		  looger.info("this is payload of create user:{}",emp);
          emp.setCreated_at(getCurrentTime());
		 Employee e= this.employeeServiceImp.createEmp(emp);
		
		return new ResponseEntity<Employee>(e,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getByIdEmployee(@PathVariable int id){
		System.out.println("Hello admin");
	Employee e=this.employeeServiceImp.getByIdEmployee(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(e);
	}
	
	
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
	
	@PreAuthorize("hasRole('ADMIN') || hasRole('MANAGER')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmp(@Valid @RequestBody Employee emp,@PathVariable int id,BindingResult bindingResult){
		
		   if(bindingResult.hasErrors()) {
				return new ResponseEntity<ApiResponse>(new ApiResponse("Validation Errors"+bindingResult.getAllErrors(),false),HttpStatus.CONFLICT);
			}
		  looger.info("this is payload of create user:{}",emp);
          emp.setCreated_at(getCurrentTime());
		 Employee e= this.employeeServiceImp.updateEmployee(emp, id);
		
		return new ResponseEntity<Employee>(e,HttpStatus.CREATED);
	}
	
	
	@GetMapping("role/all")
	public ResponseEntity<List<Role>> getAllRole(){
		List<Role> e=this.roleServiceImp.getAllRole();
		return ResponseEntity.ok(e);
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deleteEmployee(@PathVariable int id){
		this.employeeServiceImp.deleteEmployee(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	 public LocalDateTime getCurrentTime() {
		 LocalDateTime currentTime = LocalDateTime.now();
	        return currentTime;
	    }
}
