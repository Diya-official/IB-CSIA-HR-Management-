package com.backend.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.erp.dao.DataOperationhr_manager;
import com.backend.erp.entity.hr_manager;


@Service
public class Hr_managerServicesImpl implements Hr_managerServices{

	@Autowired
	DataOperationhr_manager dbhr; 
	
	@Override
	public void insertHr_manager(hr_manager h) {
		// TODO Auto-generated method stub
		dbhr.save(h);
		
		
	}

	@Override
	public void deleteHr_manager(String email) {
		// TODO Auto-generated method stub
		dbhr.deleteById(email);
		
	}

	@Override
	public List<hr_manager> getHr_managers() {
		// TODO Auto-generated method stub
		return dbhr.findAll();
	}

	@Override
	public hr_manager getHr_manager(String email) {
		// TODO Auto-generated method stub
		return dbhr.findById(email).get();
	}

}
