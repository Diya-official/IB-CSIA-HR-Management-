package com.backend.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.backend.erp.entity.hod;

@Service
public interface DataOperationhod extends JpaRepository<hod,String>{

}
