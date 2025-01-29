package com.backend.erp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.erp.entity.faculty;


@Service
public interface FacultyServices {

	public boolean insertFaculty(faculty f1); 
	public void deleteFaculty(String email);
	public List<faculty> getFaculties();
	public faculty getFaculty(String email);
	public boolean updateFaculty(faculty f1);
	
}
