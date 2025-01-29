package com.backend.erp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LeaveRecord {

	  @Id
	  @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("Exigency")
    private int Exigency;

    @JsonProperty("Earned")
    private int Earned;

    @JsonProperty("Work_From_Home")
    private int Work_From_Home;

    @JsonProperty("Comp_off")
    private int Comp_off;

    @JsonProperty("Outdoor_Duty")
    private int Outdoor_Duty;

    @JsonProperty("Maternity_Leave")
    private int Maternity_Leave;
	
	
	
	
	
	public LeaveRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public LeaveRecord(String email, String name, int exigency, int earned, int work_From_Home, int comp_off,
			int outdoor_Duty, int maternity_Leave) {
		super();
		this.email = email;
		this.name = name;
		Exigency = exigency;
		Earned = earned;
		Work_From_Home = work_From_Home;
		Comp_off = comp_off;
		Outdoor_Duty = outdoor_Duty;
		Maternity_Leave = maternity_Leave;
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
	public int getExigency() {
		return Exigency;
	}
	public void setExigency(int exigency) {
		Exigency = exigency;
	}
	public int getEarned() {
		return Earned;
	}
	public void setEarned(int earned) {
		Earned = earned;
	}
	public int getWork_From_Home() {
		return Work_From_Home;
	}
	public void setWork_From_Home(int work_From_Home) {
		Work_From_Home = work_From_Home;
	}
	public int getComp_off() {
		return Comp_off;
	}
	public void setComp_off(int comp_off) {
		Comp_off = comp_off;
	}
	public int getOutdoor_Duty() {
		return Outdoor_Duty;
	}
	public void setOutdoor_Duty(int outdoor_Duty) {
		Outdoor_Duty = outdoor_Duty;
	}
	public int getMaternity_Leave() {
		return Maternity_Leave;
	}
	public void setMaternity_Leave(int maternity_Leave) {
		Maternity_Leave = maternity_Leave;
	}
	
	



}
