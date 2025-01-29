package com.backend.erp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.backend.erp.entity.Leaves;

@Service
public interface LeaveServices {

	public boolean insertLeaves(Leaves leave); 
	public boolean updateLeaves(Leaves leave);
	public void deleteLeaves(String leave_id);
	public Leaves getLeaves(String email);
	public List<Leaves> getAllLeaves(); 
	
	
}
