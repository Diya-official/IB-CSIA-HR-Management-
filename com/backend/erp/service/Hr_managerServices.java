package com.backend.erp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.erp.entity.hr_manager;


@Service
public interface Hr_managerServices {

	public void insertHr_manager(hr_manager h);
    public void deleteHr_manager(String email);
    public List<hr_manager> getHr_managers();
    public hr_manager getHr_manager(String email);
 
}

