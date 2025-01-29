package com.backend.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.erp.dao.DataOperationhod;
import com.backend.erp.entity.hod;


@Service
public class HodServicesImpl implements HodServices{

	@Autowired
	DataOperationhod dbHod ;
	
	@Override
	public boolean insertHod(hod h1) {
		// TODO Auto-generated method stub
		hod h2= dbHod.save(h1);
		
		if(h2.getName().equalsIgnoreCase(h1.getName())) {
			return true;
		}
		
		return false;
	}

	@Override
	public void deleteHod(String email) {
		// TODO Auto-generated method stub
		dbHod.deleteById(email);
	}

	@Override
	public List<hod> getAllHod() {
		// TODO Auto-generated method stub
		return dbHod.findAll();
	}

	@Override
	public hod getHod(String email) {
		// TODO Auto-generated method stub
		return dbHod.findById(email).get();
	}

}
