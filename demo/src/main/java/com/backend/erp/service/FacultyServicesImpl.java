package com.backend.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.erp.dao.DataOperationFaculty;
import com.backend.erp.entity.faculty;


@Service
public class FacultyServicesImpl implements FacultyServices{

	@Autowired
	DataOperationFaculty dbFac;
	
	
	@Override
	public boolean insertFaculty(faculty f1) {
		// TODO Auto-generated method stub
		
		System.out.println("The insert Faculty called !!!!!");
		System.out.println("Name : "+f1.getName());
		System.out.println("Dept : "+f1.getDept());
		System.out.println("Employee Id :"+f1.geteId());
		System.out.println("Email :"+f1.getEmail());
		System.out.println("PH number :"+f1.getPhoneNumber());
		System.out.println("DOB :"+f1.getDateOfBirth());
		System.out.println("DOJ :"+f1.getDateOfJoining());
		faculty f2=dbFac.save(f1);
		System.out.println("End of method");
		
		if(f2.getName().equalsIgnoreCase(f1.getName())) {
			
			return true; 
			
		}
		
		return false;
		
	}

	@Override
	public void deleteFaculty(String email) {
		// TODO Auto-generated method stub
		dbFac.deleteById(email);
		
	}

	@Override
	public List<faculty> getFaculties() {
		// TODO Auto-generated method stub
		
		return dbFac.findAll();
	}

	@Override
	public faculty getFaculty(String email) {
		// TODO Auto-generated method stub
		return dbFac.findById(email).get();
	}

	@Override
	public boolean updateFaculty(faculty f1) {
		// TODO Auto-generated method stub
		faculty f= dbFac.save(f1);
		
		if(f.getEmail().equalsIgnoreCase(f1.getEmail())) {
			
			return true;
			
		}
		
		return false;
	}

}
