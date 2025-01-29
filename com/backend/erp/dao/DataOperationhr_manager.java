package com.backend.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.backend.erp.entity.hr_manager;

@Service
public interface DataOperationhr_manager extends JpaRepository<hr_manager,String>{

}
