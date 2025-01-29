package com.backend.erp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class hod {

	private String name; 
 	private LocalDate date_of_joining;
 	private LocalDate date_of_birth;
 	@Id
 	private String email;
	private String e_id;
	private String ph_number;
	private String address; 
	private String dept_hod_of;
	private String password; 
	
	
	
	

	public hod(String name, LocalDate date_of_joining, LocalDate date_of_birth, String email, String e_id,
			String ph_number, String address, String dept_hod_of) {
		super();
		this.name = name;
		this.date_of_joining = date_of_joining;
		this.date_of_birth = date_of_birth;
		this.email = email;
		this.e_id = e_id;
		this.ph_number = ph_number;
		this.address = address;
		this.dept_hod_of = dept_hod_of;
	}
	public hod() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate_of_joining() {
		return date_of_joining;
	}
	public void setDate_of_joining(LocalDate date_of_joining) {
		this.date_of_joining = date_of_joining;
	}
	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getE_id() {
		return e_id;
	}
	public void setE_id(String e_id) {
		this.e_id = e_id;
	}
	public String getPh_number() {
		return ph_number;
	}
	public void setPh_number(String ph_number) {
		this.ph_number = ph_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDept_hod_of() {
		return dept_hod_of;
	}
	public void setDept_hod_of(String dept_hod_of) {
		this.dept_hod_of = dept_hod_of;
	}
 	
}
