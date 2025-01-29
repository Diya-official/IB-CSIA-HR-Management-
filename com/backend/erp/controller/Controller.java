package com.backend.erp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.erp.entity.LeaveRecord;
import com.backend.erp.entity.Leaves;
import com.backend.erp.entity.faculty;
import com.backend.erp.entity.hod;
import com.backend.erp.entity.hr_manager;
import com.backend.erp.service.FacultyServices;
import com.backend.erp.service.FileOperation;
import com.backend.erp.service.HodServices;
import com.backend.erp.service.Hr_managerServices;
import com.backend.erp.service.LeaveRecordService;
import com.backend.erp.service.LeaveServices;

import jakarta.transaction.Transactional;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class Controller {

	@Autowired
	HodServices hodSer;
	@Autowired
	Hr_managerServices hms ; 
	@Autowired
	FacultyServices fs; 
    @Autowired
	FileOperation fileoperation;
    @Autowired
    LeaveServices leaveServices ; 
    @Autowired
    LeaveRecordService lrs; 
    
    @Transactional
    @GetMapping("/hod-forgetPassword")
    public ResponseEntity<Map<String,String>> hodForgetPassword(@RequestParam("email") String email,@RequestParam("password") String password){
    	HashMap<String,String>  response=new HashMap<String,String>(); 
    	System.out.println("Method called for changing password's of hod");
    	hod h=hodSer.getHod(email); 
    	
    	h.setPassword(password);
    	
    	if(hodSer.insertHod(h)) {
    		
    		response.put("message", "Password updated !!!");
    		ResponseEntity.ok(response);    		
    	}
    	
    	
    	response.put("message", "Password updated !!!");
    	return ResponseEntity.ok(response);
    }
    
    @GetMapping("/hr-forgetPassword")
    ResponseEntity<Map<String,String>> hrForgetPassword(@RequestParam("email") String email,@RequestParam("password") String password){
    	
    	System.out.println("EMAIL :"+email + " PASSWORD :"+password);
    	
    	HashMap<String,String> response=new HashMap<String,String>(); 
    	hr_manager hr=hms.getHr_manager(email);
    	hr.setPassword(password);
    	
    	hms.insertHr_manager(hr);
    	
    	response.put("message", "Password updated !!!");
    	return ResponseEntity.ok(response);
    	
    }
	
    
    @GetMapping("/Fac-forgetPassword")
    public ResponseEntity<Map<String,String>> facForgetPassword(@RequestParam("email") String email,@RequestParam("password") String password){
    	
    	faculty fac=fs.getFaculty(email);
    	fac.setPassword(password);
    	HashMap<String,String> response=new HashMap<String,String>();
    	
    	if(fs.updateFaculty(fac)) {
    		
    		response.put("message", "Password changed sucessfully");
    		return ResponseEntity.ok(response);
    		
    	}
    	
    	response.put("message", "Cannot change password !! ");
    	return ResponseEntity.ok(response); 
    } 
    
    
	@GetMapping("/hodLogin")
	public boolean CheckHod(@RequestParam String email,@RequestParam String password) {
		
		hod h=hodSer.getHod(email);
		
		if(h.getEmail().equalsIgnoreCase(email) && h.getPassword().equalsIgnoreCase(password)) {
			
			return true;
			
		}
		
		
		return false;
		
	}
	
	@GetMapping("/hrManagerLogin")
	public boolean CheckHrManager(@RequestParam String email,@RequestParam String password) {
		
		hr_manager hm=hms.getHr_manager(email);
		
		if(hm.getEmail().equalsIgnoreCase(email)&& hm.getPassword().equalsIgnoreCase(password)) {
			
			return true; 
			
		}
		
		return false;
	}
	
	
	@GetMapping("/facultyLogin")
	public boolean checkFaculty(@RequestParam String email,@RequestParam String password) {
		
		faculty fac=fs.getFaculty(email); 
		
		if(fac.getEmail().equalsIgnoreCase(email) && fac.getPassword().equalsIgnoreCase(password)) {
			
			return true;
			
		}
 		
		
		return false;
	}
	
	
	@Transactional
	@PostMapping("/insertHr_manager")
	ResponseEntity<Map<String,String>> insertHr(@RequestBody hr_manager hr){
		
		hms.insertHr_manager(hr);
		HashMap<String,String> response=new HashMap<String,String>(); 
		
		response.put("message", "Personnel inserted sucessfully !!");
		
		
		return ResponseEntity.ok(response) ; 
		
		
	}
	
	@Transactional
	@PostMapping("/insertFaculty")
	public ResponseEntity<Map<String, String>> insertFaculty(@RequestBody faculty fac) {
	    System.out.println("Adding Faculty: " + fac);
	    fs.insertFaculty(fac);
	    System.out.println("Faculty added.");
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Faculty added successfully");
	    return ResponseEntity.ok(response);
	}
	
	
	@Transactional
	@PostMapping("/insertHod")
	public ResponseEntity<Map<String, String>> insertHod(@RequestBody hod h) {
		
		
		hodSer.insertHod(h);
			
			
			 Map<String, String> response = new HashMap<>();
			    response.put("message", "Faculty added successfully");
			    return ResponseEntity.ok(response);
		
	}
	
	
	@Transactional
	@GetMapping("/deleteFaculty")
	public ResponseEntity<Map<String,String>> delteFaculty(@RequestParam String email) {
		
		fs.deleteFaculty(email);
		HashMap<String,String> response=new HashMap<String,String>();
		response.put("message","Faculty deleted sucessfully !!!");
		fs.deleteFaculty(email);
		return ResponseEntity.ok(response);
		
	}
	
	
	@Transactional
	@GetMapping("/deleteHod")
	public ResponseEntity<Map<String,String>> deleteHod(String email) {
		
		hodSer.deleteHod(email);
		HashMap<String,String> response=new HashMap<String,String>();
		response.put("message", "Hod deleted sucessfully");
		return ResponseEntity.ok(response);
		
	}
	
	@Transactional
	@GetMapping("/wholeData")
	public List getWholeData() {
		
		List ls=new ArrayList();
		
		ls.add(hodSer.getAllHod());
		ls.add(fs.getFaculties());
		ls.add(hms.getHr_managers());
		
		
		return ls;
		
		
	}
	
	
	@PostMapping("/uploadfile")
	public ResponseEntity readFile(@RequestParam("file") MultipartFile file) throws IOException {
		
		if(file.isEmpty()) {
			
			System.out.println("The sent file is empty !!!!");
			
		}
		
			
			System.out.println("Started to print the file");
			
			System.out.println("Printed the whole file");
			file= fileoperation.updateFile(file);
		
		
	             return ResponseEntity.ok()
	            .header("Content-Disposition", "attachment; filename=" + file.getOriginalFilename())
	            .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
	            .body(file.getBytes());
	}
	
	
	@PostMapping("/insertLeaves")
	public ResponseEntity<Map<String,String>> insertLeaves(@RequestBody Leaves leaves){
		
		if(leaveServices.insertLeaves(leaves)) {
			
			HashMap<String , String> response= new HashMap<String,String>();
			response.put("message", "Leave status updated !!!");
			
			return ResponseEntity.ok(response); 
			
		}
		
		HashMap<String,String> response = new HashMap<String,String>(); 
		response.put("Error : ", "Leave cannot be inserted");
		
		return ResponseEntity.ok(response);
		
		
	}
	
	
	@GetMapping("/deleteLeave")
	public ResponseEntity<Map<String,String>> deleteLeaves(@RequestParam("leave_id") String leave_id){
		
		HashMap<String,String> response= new HashMap<String,String>();
		
		response.put("message", "Leave request deleted ");
		
		leaveServices.deleteLeaves(leave_id);
		
		return ResponseEntity.ok(response) ; 
		
	}
	
	@PostMapping("/updateLeaveRequest")
	public ResponseEntity<Map<String,String>> updateLeaveRequest(@RequestBody Leaves leave) {
		HashMap<String ,String> response = new HashMap<String,String>(); 
		
		
		if(leaveServices.updateLeaves(leave)) {
			
			response.put("Message", "Leave application updated sucessfully ");
		    return ResponseEntity.ok(response);	
		} 
		
		
		
		response.put("Message", "Leave application cannot be approved due to low leave balance");
		
		return ResponseEntity.ok(response) ; 
		
	}
	
	
	@PostMapping("/GetAllLeaves")
	public List<Leaves> getAllLeaves(){
		
		return leaveServices.getAllLeaves();
		
	}
	
	
	
	
	@GetMapping("/findLeaveRecord")
	public LeaveRecord getLeaveRecord(String email) throws UnsupportedEncodingException {
		
		
		String decodedEmail=URLDecoder.decode(email, "UTF-8");
		System.out.println("Email"+decodedEmail);
		return lrs.findLeaveRecord(decodedEmail);
		
	}
	
	@GetMapping("/findLeaves")
	public Leaves getLeaves(@RequestParam("leave_id") String leave_id) throws UnsupportedEncodingException {
		

		String d_leave_id=URLDecoder.decode(leave_id, "UTF-8");
		System.out.println("Leave_id : "+d_leave_id);
		
		Leaves leaves  =  leaveServices.getLeaves(d_leave_id);
		
		
		return leaves; 
	} 
	
	@PostMapping("/insertLeaveRecord")
	public ResponseEntity<Map<String,String>> insertLeaveRecord(@RequestBody LeaveRecord leaveRecord) {
		
       HashMap<String,String> response = new HashMap<String,String>(); 
       
       System.out.println("Printing leave record : ");
       System.out.println(leaveRecord.getComp_off());
       System.out.println(leaveRecord.getEarned());
       System.out.println(leaveRecord.getExigency());
       System.out.println(leaveRecord.getMaternity_Leave());
       System.out.println(leaveRecord.getOutdoor_Duty());
       System.out.println(leaveRecord.getWork_From_Home());
       
       
       if(lrs.insertLeaveRecord(leaveRecord)) {
    	   response.put("Message", "Leave record updated !!!");
    	   return ResponseEntity.ok(response);
    	   
       }
       
       response.put("message", "Leave record cannot be updated !!!"); 
		
		return ResponseEntity.ok(response) ; 
	}
	
	
	@PostMapping("/updateLeaveRecord")
	public ResponseEntity<Map<String,String>> updateLeaveRecord(@RequestBody LeaveRecord leaveRecord){
		HashMap<String,String> response=new HashMap<String,String>(); 
		
		if(lrs.updateLeaveRecord(leaveRecord)) {
			
			response.put("Message", "Leave record altered sucessfully !!!"); 
			return ResponseEntity.ok(response);
			
		}
		
		response.put("Message", "Leave record cannot be  altered !!!"); 
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/getAllLeaveRecords")
	List<LeaveRecord> getAllLeaveRecord(){
		
		return lrs.getAllLeaveRecords();
		
	}
	
	
	@GetMapping("/getRecord")
	public ResponseEntity<byte[]> getRecord(@RequestParam("email") String email) throws IOException {
	    MultipartFile file = fileoperation.getRecord(email);

	    if (file == null) {
	        return ResponseEntity.notFound().build();
	    }

	    // Return the file as a downloadable attachment
	    return ResponseEntity.ok()
	            .header("Content-Disposition", "attachment; filename=" + file.getOriginalFilename())
	            .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
	            .body(file.getBytes());
	}

	
	
	
}
