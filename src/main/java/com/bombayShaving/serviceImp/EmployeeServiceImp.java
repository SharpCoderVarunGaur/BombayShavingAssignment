package com.bombayShaving.serviceImp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bombayShaving.entites.Employee;
import com.bombayShaving.exception.ApiResponse;
import com.bombayShaving.exception.EmailSameException;
import com.bombayShaving.exception.ResourceNotFoundException;
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
   
	@Autowired
	private Employee empe;
 	@Override
	public Employee getByIdEmployee(int id) {
		Optional<Employee> e=this.employeeRepo.findById(id);
	     if(!e.isPresent()) {
	    	 System.out.println("Not Found");
   	 throw new ResourceNotFoundException("Employee not found","empId: ",id);
    }
		return e.get();
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> e=this.employeeRepo.findAll();	
		return e;
	}

	@Override
	public Employee updateEmployee(Employee emp,int id) {
	      
		if(emp.getEmail()!=null) {
			empe= this.employeeRepo.findByEmail(emp.getEmail());
			if(empe !=null ) {
				if(empe.getId()!=id) {
					System.out.println("HEll1");
					throw new EmailSameException();	
				}
				}
		}
			 
				
				
			
		
		
		
		   Optional<Employee> e =this.employeeRepo.findById(id);
		     if(!e.isPresent()) {
		    	 throw new ResourceNotFoundException("Employee not found","empId: ",id);
		     }
  
		     Employee employee=e.get();
		     
		     if(emp.getName()!=null) {
		    	 employee.setName(emp.getName());	 
		     }
		    
		     if(emp.getEmail()!=null) {
		    	 employee.setEmail(emp.getEmail());	 
		     }
		   
		     employee.setCreated_at(getCurrentTime());
	       Employee updatedEmployee=this.employeeRepo.save(employee);
		   return updatedEmployee;
	
	}
	
	public LocalDateTime getCurrentTime() {
		LocalDateTime currentTime = LocalDateTime.now();
        return currentTime;
    }

	@Override
	public void deleteEmployee(int id) {
		 Optional<Employee> e =this.employeeRepo.findById(id);
		  if(!e.isPresent()) {
		    	 throw new ResourceNotFoundException("Employee not found","empId:",id);
		     }
              this.employeeRepo.delete(e.get());
	}

}
