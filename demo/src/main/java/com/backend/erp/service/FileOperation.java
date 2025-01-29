package com.backend.erp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface FileOperation {

	void printFile(MultipartFile file); 
	MultipartFile updateFile(MultipartFile file); 
	MultipartFile getRecord(String email); 
}
