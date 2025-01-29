package com.backend.erp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.backend.erp.entity.LeaveRecord;

@Service
public interface DataOperationLeaveRecord extends JpaRepository<LeaveRecord,String> {

}
