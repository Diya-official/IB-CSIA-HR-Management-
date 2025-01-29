package com.backend.erp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.backend.erp.entity.LeaveRecord;

@Service
public interface LeaveRecordService {
	
	
	
	
	public boolean insertLeaveRecord(LeaveRecord leaveR) ;
	public boolean updateLeaveRecord(LeaveRecord leaveR);
	public List<LeaveRecord> getAllLeaveRecords(); 
	public void deleteLeaveRecord(LeaveRecord leaveR); 
	public LeaveRecord findLeaveRecord(String email);

}
