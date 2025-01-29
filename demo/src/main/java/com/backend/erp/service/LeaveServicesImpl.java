package com.backend.erp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.erp.dao.DataOperationLeaves;
import com.backend.erp.entity.LeaveRecord;
import com.backend.erp.entity.Leaves;


@Service
public class LeaveServicesImpl implements LeaveServices{

	@Autowired
	DataOperationLeaves dol ; 
	@Autowired
	LeaveRecordService lrs; 
	
	
	
	@Override
	public boolean insertLeaves(Leaves leave) {
		
		
		Leaves l1=dol.save(leave);
	    	
		if(l1.getEmail().equalsIgnoreCase(leave.getEmail())) {
			
			return true;
			
		}
		
		return false;
	}

	
	@Override
	public boolean updateLeaves(Leaves leave) {
        
		System.out.println("==================================================================");
		System.out.println("Email :"+leave.getEmail());
		System.out.println("Days on leave :"+leave.getDaysOnLeave());
		System.out.println("Email :"+leave.getName());
		System.out.println("Reason :"+leave.getReason());
		System.out.println("Status :"+leave.getStatus());
		System.out.println("Type of leave  :"+leave.getTypeOfLeave());
		System.out.println("Date Of Leave  :"+leave.getDate_of_leave());
		System.out.println("Leave_id :"+leave.getLeave_id());
		System.out.println("===================================================================");
		
		
		
		Leaves l1=dol.save(leave);
		
		if(leave.getStatus().equalsIgnoreCase("Accepted")) {
			
			System.out.println("Email in if :"+leave.getEmail());
			LeaveRecord leaveRecord=lrs.findLeaveRecord(leave.getEmail());
			
			System.out.println("Fetched leave record");
			String typeOfLeave=leave.getTypeOfLeave(); 
			System.out.println("Type of leave :"+typeOfLeave );
		    int daysOnLeave=leave.getDaysOnLeave();
		    System.out.println("Data fetched via the leave : "+typeOfLeave+"|"+daysOnLeave);
		    
		    try {
		    switch(typeOfLeave) {
		    
		    case "exigency":leaveRecord.setExigency(leaveRecord.getExigency()-daysOnLeave);
		    System.out.println("Exigency leave approved  : remaining leaves : "+leaveRecord.getExigency());
		    if(leaveRecord.getExigency()<0) { return false;}
		    break ; 
		    
		    case "comp_off":leaveRecord.setComp_off(leaveRecord.getComp_off()-daysOnLeave);
		    System.out.println("Comp_off approved  : remaining leaves : "+leaveRecord.getComp_off());
		    if(leaveRecord.getComp_off()<0) { return false ; }
		    break  ; 
		    
		    case "earned":leaveRecord.setEarned(leaveRecord.getEarned()-daysOnLeave);
		    System.out.println("Earned leave approved  : remaining leaves : "+leaveRecord.getEarned());
		    if(leaveRecord.getEarned()<0) { return false; }
		    break ; 
		    
		    case "maternity_leave":leaveRecord.setMaternity_Leave(leaveRecord.getMaternity_Leave()-daysOnLeave);
		    System.out.println("Maternity leave approved  : remaining leaves : "+leaveRecord.getMaternity_Leave());
		    if(leaveRecord.getMaternity_Leave()<0) { return false; }
		    break ; 
		    
		    case "outdoor_duty":leaveRecord.setOutdoor_Duty(leaveRecord.getOutdoor_Duty()-daysOnLeave);
		    System.out.println("Outdoor duty approved  : remaining leaves : "+leaveRecord.getOutdoor_Duty());
		    if(leaveRecord.getOutdoor_Duty()<0) { return false; }
		    break ; 
		    
		    case "work_from_home":leaveRecord.setWork_From_Home(leaveRecord.getWork_From_Home()-daysOnLeave);
		    System.out.println("Work from home  approved  : remaining leaves : "+leaveRecord.getWork_From_Home());
		    if(leaveRecord.getWork_From_Home()<0) { return false; }
		    break ; 
		    
		    }}
		    catch(Exception ex) {
		    	
		    	System.out.println(ex.getMessage()); 
		    	return false ; 
		    	
		    }
	  		
			lrs.updateLeaveRecord(leaveRecord);
			
			
		}
		
		
		
		if(l1.getEmail().equalsIgnoreCase(leave.getEmail())) {
			
			return true;
			
		}
		
		return false;
	}

	
	
	@Override
	public void deleteLeaves(String leave_id) {
		// TODO Auto-generated method stub
		
		dol.deleteById(leave_id);
		
	}

	
	
	@Override
	public Leaves getLeaves(String leave_id) {

        Leaves leaves=(Leaves)dol.findById(leave_id).get(); 
		return leaves;
	}

	
	@Override
	public List<Leaves> getAllLeaves() {

		return dol.findAll();
	
	}

}
