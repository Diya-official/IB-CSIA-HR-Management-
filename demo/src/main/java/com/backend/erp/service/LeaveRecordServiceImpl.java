package com.backend.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.erp.dao.DataOperationLeaveRecord;
import com.backend.erp.entity.LeaveRecord;

@Service
public class LeaveRecordServiceImpl implements LeaveRecordService{

	@Autowired
	DataOperationLeaveRecord dlr;
	
	
	@Override
	public boolean insertLeaveRecord(LeaveRecord leaveR) {
		LeaveRecord lr=dlr.save(leaveR);
		
		
		
		if (lr.getEmail().equalsIgnoreCase(leaveR.getEmail())) {
			
			return true; 
			
		}
		return false;
	}

	@Override
	public boolean updateLeaveRecord(LeaveRecord leaveR) {
        
		LeaveRecord lr=dlr.save(leaveR);
		
		if (lr.getEmail().equalsIgnoreCase(leaveR.getEmail())) {
			
			return true; 
			
		}
		return false;

	}

	@Override
	public List<LeaveRecord> getAllLeaveRecords() {
		
		return dlr.findAll();
	}

	@Override
	public void deleteLeaveRecord(LeaveRecord leaveR) {
		
		dlr.delete(leaveR);
		
	}
	
	public LeaveRecord findLeaveRecord(String email) {
		
		return dlr.findById(email).get();
		
	}
 
	
}
