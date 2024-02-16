package com.bombayShaving.entites;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Component
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 
	
    private String name;
    @Email(message = "Email should be valid in @/.com")
    private String email; 
    private LocalDateTime  created_at;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime localTime) {
		this.created_at = localTime;
	}
	
	public Employee(int id, String name, @Email(message = "Email should be valid in @/.com") String email,
			LocalDateTime created_at) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.created_at = created_at;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", created_at=" + created_at + "]";
	}
	
	
}
