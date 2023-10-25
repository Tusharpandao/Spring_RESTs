package com.jspiders.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.jspiders.springrest.pojo.AdminPOJO;
import com.jspiders.springrest.response.AdminResponse;
import com.jspiders.springrest.service.AdminService;

@Controller
public class Admincontroller {
	@Autowired
	private AdminService service;
	
	@PostMapping(path = "/creatAccount",consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminResponse> createAccount(@RequestBody AdminPOJO pojo){
		
		AdminPOJO admin=service.createAccount(pojo);
		//success
		if(admin != null) {
			
			return new ResponseEntity<AdminResponse>(new AdminResponse("Account created Successfully ", admin),HttpStatus.ACCEPTED);
			
		}
		return new ResponseEntity<AdminResponse>(new AdminResponse("Account not created !!!",null),HttpStatus.NOT_ACCEPTABLE);
		
	}
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdminResponse> login(@RequestBody AdminPOJO pojo){
		AdminPOJO admin=service.login(pojo);
		if (admin != null) {
			
			return new ResponseEntity<AdminResponse>(new AdminResponse("Login successfully", admin),HttpStatus.OK);
			
		}
		return new ResponseEntity<AdminResponse> (new AdminResponse("login failed", null),HttpStatus.NOT_FOUND);
		
		
	}
	
	
}
