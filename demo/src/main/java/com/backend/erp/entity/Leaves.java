package com.backend.erp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Leaves {

	@Id
	private String leave_id; 
	private String email;
	private String name; 
	private LocalDate date_of_leave;
	private String typeOfLeave ; 
	private String status;
	private String reason;
	private int daysOnLeave; 
	private String dept; 
	
	public Leaves() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Leaves(String email, String name,String leave_id, LocalDate date_of_leave, String typeOfLeave, String status,String reason,int daysOnLeave,String dept) {
		super();
		this.leave_id=leave_id; 
		this.dept=dept; 
		this.reason=reason;
		this.email = email;
		this.name = name;
		this.date_of_leave = date_of_leave;
		this.typeOfLeave = typeOfLeave;
		this.status = status;
		this.daysOnLeave=daysOnLeave; 
	}
	
	

	
	
	
	
	public String getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(String leave_id) {
		this.leave_id = leave_id;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getDaysOnLeave() {
		return daysOnLeave;
	}

	public void setDaysOnLeave(int daysOnLeave) {
		this.daysOnLeave = daysOnLeave;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate_of_leave() {
		return date_of_leave;
	}

	public void setDate_of_leave(LocalDate date_of_leave) {
		this.date_of_leave = date_of_leave;
	}

	public String getTypeOfLeave() {
		return typeOfLeave;
	}

	public void setTypeOfLeave(String typeOfLeave) {
		this.typeOfLeave = typeOfLeave;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
}
