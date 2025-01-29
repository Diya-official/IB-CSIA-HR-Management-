package com.backend.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.backend.erp.entity.faculty;

@Service
public interface DataOperationFaculty  extends JpaRepository<faculty,String>{

}
