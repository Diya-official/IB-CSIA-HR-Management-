package com.backend.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import com.backend.erp.entity.LeaveRecord;
import com.backend.erp.entity.Leaves;
import com.backend.erp.entity.faculty;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public  class FileOperationImpl implements FileOperation{
	
	
	
	@Autowired
	LeaveServices leaveService; 
	@Autowired
	FacultyServices fs; 
	@Autowired
	LeaveRecordService lrs;
	

	public void printFile(MultipartFile file) {
		
		
		try {
			
			Workbook workbook= new  XSSFWorkbook(file.getInputStream());
			Sheet sheet=workbook.getSheetAt(0);
			
			Row row=sheet.getRow(7);
			
			Cell cell=row.getCell(1);
			System.out.println("Emp Name : "+cell.toString());
			System.out.println("Attendance :");
			String name=cell.toString();
			
			for( int i=2 ; i <=31 ; i++) {
				
				cell = row.getCell(i); 
				System.out.print(cell.toString()+"  ");
				
			}
			
		}
		catch(Exception ex) {
			
			System.out.println(ex.getMessage());
			
		}
		
		
	}
	
	public MultipartFile updateFile(MultipartFile file ) {
         
 
		int leaveFromCell,numberOfLeavesTaken; 
		String leaveInitial=new String(); 
		
		try {
            List<faculty> facList=fs.getFaculties();
			Workbook workbook = new  XSSFWorkbook(file.getInputStream());
			Sheet sheet=workbook.getSheetAt(0); 
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			LocalDate parsedDate;
			
			
			Row row; // Stores particular row and compares name
			Cell cell;   // Stores cell content and is used in real time 
			
			
			List<Leaves> leavesList = leaveService.getAllLeaves(); 
			List<Leaves> ApprovedList= new ArrayList<Leaves>(); 
			
			
			for(Leaves leave : leavesList) {
				
				if(leave.getStatus().equalsIgnoreCase("Accepted") || leave.getStatus().equalsIgnoreCase("Approved")) {
					
					ApprovedList.add(leave); 
				 
				}
				
				
			}
			
			System.out.println("Printing accepted list !!! : "); 
			
			for( Leaves leave : ApprovedList) {
				System.out.println("====================================================================================================");
				System.out.println("Name : "+leave.getName()+"  Email : "+leave.getEmail()+" Status :"+leave.getStatus()+"  Date of leave : "+leave.getDate_of_leave()); 
				
				//Data required to manipulate the file contents 
				String name= leave.getName(); 
				String typeOfLeave = leave.getTypeOfLeave(); 
				int numberOfLeave = leave.getDaysOnLeave(); 
				LocalDate dateOnLeave = leave.getDate_of_leave(); 
				dateOnLeave=LocalDate.parse(dateOnLeave.format(formatter),formatter); 
				System.out.println("Printing date");
				System.out.println(dateOnLeave.toString());
				
				switch(typeOfLeave) {
				
				case "comp_off":leaveInitial="CO";
				break ; 
				
				case "earned":leaveInitial="PL";
				break ; 
				
				case "exigency":leaveInitial="EL";
				break;
				
				case "maternity_leave":leaveInitial="ML";
				break; 
				
				case "outdoor_duty":leaveInitial="OD";
				break;
				
				case "work_from_home":leaveInitial="WFH";
				break; 
				
				
				}
				
				for(int i=2 ; i<facList.size();i++) {
					
					
					row=sheet.getRow(i); 
					cell=row.getCell(1);
					
					
					
					if(cell.toString().equals(leave.getName())) {
						
						System.out.println("Employee found !!!!!");
						
						row=sheet.getRow(1);
						
						for(int j=2 ; j<33 ; j++) {
							
							
						   
							String dateOfLeave=dateOnLeave.format(formatter);
							cell=row.getCell(j);
							
							//Condition to avoid exception if the cell is empty 
							if( cell == null ||cell.toString().equals("")) {
								
								break ; 
								
							}
							
							System.out.println("Local date :"+dateOfLeave + "   Date in excel :"+cell.toString());
							
							if(cell.toString().equals(dateOfLeave)) {
								
								System.out.println("Found the day on which leave starts !!!!");
								
								//Inserting 
								
								row=sheet.getRow(i);
								leaveFromCell=j; 
								numberOfLeavesTaken=leave.getDaysOnLeave();
								
								while(numberOfLeavesTaken!=0) {
									
									cell=row.getCell(leaveFromCell);
									leaveFromCell++; 
									System.out.println("Row number :"+i);
									System.out.println("Column number :"+leaveFromCell);
									System.out.println(cell.toString());
									if(cell==null || cell.toString().equals("")) {
									    
										System.out.println("The cell with empty value ");
										continue; 
										
									}
									else if(cell.toString().trim().equalsIgnoreCase("a")) {
										
										cell.setCellValue(leaveInitial);
										
										numberOfLeavesTaken--; 
										System.out.println("setted a single cell value to type of leave"+numberOfLeavesTaken+"yet to go !!!");
										
									}
									else if(cell.toString().trim().equalsIgnoreCase("p")) {
										System.out.println("Into p");
										continue ; 
									}
									
								}
								
								System.out.println("Outside while loop!!!");
								
							}
							
							
						   
						}
						
						System.out.println("OutSide the manual for loop !!!");
						
					}
					
					
					
				
				}
				
				System.out.println("Outside the for loop !!!!");
				
				
				
				}
			
			System.out.println("Outside the for-in loop !!!!");
			
			
			   ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		        workbook.write(outputStream);
		        System.out.println("Created output stream and sent the data");

		        // Create the actual MultipartFile using the output stream
		        return new MultipartFile() {
		            @Override
		            public String getName() {
		                return "reportOfFaculty.xlsx";
		            }

		            @Override
		            public String getOriginalFilename() {
		                return "report.xlsx";
		            }

		            @Override
		            public String getContentType() {
		                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		            }

		            @Override
		            public byte[] getBytes() throws IOException {
		                return outputStream.toByteArray();
		            }

		            @Override
		            public InputStream getInputStream() throws IOException {
		                return new ByteArrayInputStream(outputStream.toByteArray());
		            }

		            @Override
		            public long getSize() {
		                return outputStream.size();
		            }

		            @Override
		            public void transferTo(File dest) throws IOException, IllegalStateException {
		                // Implement file transfer logic if necessary
		            }

					@Override
					public boolean isEmpty() {
						// TODO Auto-generated method stub
						return false;
					}
		        };
			
			
			
			
		}
		catch(Exception ex) {
			
			
			System.out.println(ex.getMessage());
			return null; 
			
		}
		
		
		
		
		
	}

	
	
	
	@Override
	public MultipartFile getRecord(String email) {
	    // Fetch all leaves and filter them
		
		
	    List<Leaves> leaves = leaveService.getAllLeaves();
	    List<Leaves> relatedLeaves = new ArrayList<>();
	    Map<String, Integer> typeOfLeaves = new HashMap<>();
	    int totalLeavesTaken = 0;
        boolean flag=true;  
	    System.out.println("Initialization");
	    // Filter leaves based on email and status
	    for (Leaves leave : leaves) {
	        if (leave.getEmail().equalsIgnoreCase(email) &&
	            (leave.getStatus().equalsIgnoreCase("Accepted") || leave.getStatus().equalsIgnoreCase("Approved"))) {
	            relatedLeaves.add(leave);
	            flag=false; 
	        }
	    }

	    System.out.println("Sorted the records");

	    
	    
	    if(!flag) {
	    // Calculate total leaves taken and categorize them by type
	    for (Leaves leave : relatedLeaves) {
	        totalLeavesTaken += leave.getDaysOnLeave();
	        typeOfLeaves.put(
	            leave.getTypeOfLeave(),
	            typeOfLeaves.getOrDefault(leave.getTypeOfLeave(), 0) + leave.getDaysOnLeave()
	        );
	    }
	    }
	    else {
	    	totalLeavesTaken=0; 
	    	typeOfLeaves.getOrDefault("No leave taken", 0);
	    }

	    System.out.println("Calculated leaves taken"); 

	    // Generate the Excel file
	    try (Workbook workbook = new XSSFWorkbook()) {
	        Sheet sheet = workbook.createSheet("Report of" + email);

	        System.out.println("Created workbook");

	        // Add "Name"
	        Row row = sheet.createRow(0);
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Name");

	        System.out.println("Entered name to sheet");
	        cell = row.createCell(1);
	        if (!relatedLeaves.isEmpty()) {
	            cell.setCellValue(leaves.get(0).getName());
	            System.out.println("Entered name data to sheet");
	        } else {
	            cell.setCellValue("No Data Found");
	        }

	        // Add "Attendance"
	        row = sheet.createRow(1);
	        cell = row.createCell(0);
	        cell.setCellValue("Attendance");
	        System.out.println("Entered attendance to sheet");
            System.out.println("Leaves taken "+totalLeavesTaken);
            double attendance = ((double) totalLeavesTaken / 30) * 100;
// Assumes 30 days in a month
	        System.out.println("===================================================");
	        System.out.println(attendance);
	        attendance=100-attendance; 
	        cell = row.createCell(1);
	        cell.setCellValue(attendance);
	        System.out.println("Entered attendance value to sheet");

	        // Add "Type Of Leaves Taken"
	        row = sheet.createRow(2);
	        cell = row.createCell(0);
	        cell.setCellValue("Type Of Leaves taken");
	        System.out.println("Entered type of leaves to sheet");

	        int column = 1;
	        for (HashMap.Entry<String, Integer> entry : typeOfLeaves.entrySet()) {
	            cell = row.createCell(column++);
	            cell.setCellValue(entry.getKey() + "-" + entry.getValue());
	        }
	        System.out.println("Entered Type of leaves vals to sheet");

	        // Add "Total Leaves Taken"
	        row = sheet.createRow(3);
	        cell = row.createCell(0);
	        cell.setCellValue("Leaves taken");
	        System.out.println("Entered leaves taken to sheet");

	        cell = row.createCell(1);
	        cell.setCellValue(totalLeavesTaken);
	        System.out.println("Entered leaves taken value to sheet");
	        
	        row=sheet.createRow(4); 
	        cell=row.createCell(0); 
	        
	        cell.setCellValue("Leaves Remaining");
	        
	        LeaveRecord leaveRecord=lrs.findLeaveRecord(leaves.get(0).getEmail());
	        
	        cell=row.createCell(1);
	        cell.setCellValue("Exigency("+leaveRecord.getExigency()+")");
	        cell=row.createCell(2);
	        cell.setCellValue("Earned ("+leaveRecord.getEarned()+")");
	        cell=row.createCell(3);
	        cell.setCellValue("Comp off ("+leaveRecord.getComp_off()+")");
	        cell=row.createCell(4);
	        cell.setCellValue("WorkFromHome ("+leaveRecord.getWork_From_Home()+")");
	        cell=row.createCell(5);
	        cell.setCellValue("Maternity leave ("+leaveRecord.getMaternity_Leave()+")");
	        cell=row.createCell(6);
	        cell.setCellValue("Outdoor duty ("+leaveRecord.getOutdoor_Duty()+")");
	        
	        

	        // Write the workbook to a ByteArrayOutputStream
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        System.out.println("Created output stream and sent the data");

	        // Create the actual MultipartFile using the output stream
	        return new MultipartFile() {
	            @Override
	            public String getName() {
	                return "reportOfFaculty.xlsx";
	            }

	            @Override
	            public String getOriginalFilename() {
	                return "report.xlsx";
	            }

	            @Override
	            public String getContentType() {
	                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	            }

	            @Override
	            public byte[] getBytes() throws IOException {
	                return outputStream.toByteArray();
	            }

	            @Override
	            public InputStream getInputStream() throws IOException {
	                return new ByteArrayInputStream(outputStream.toByteArray());
	            }

	            @Override
	            public long getSize() {
	                return outputStream.size();
	            }

	            @Override
	            public void transferTo(File dest) throws IOException, IllegalStateException {
	                // Implement file transfer logic if necessary
	            }

				@Override
				public boolean isEmpty() {
					// TODO Auto-generated method stub
					return false;
				}
	        };
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null; // Return null in case of error
	    }
	}
	
	
}
