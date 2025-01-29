package com.backend.erp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.erp.entity.hod;

@Service
public interface HodServices {

	public boolean insertHod(hod h1);
	public void deleteHod(String email);
	public List<hod> getAllHod();
	public hod getHod(String email);
}
