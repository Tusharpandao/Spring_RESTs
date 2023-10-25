package com.jspider.cardekho_case_study_restapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jspider.cardekho_case_study_restapp.pojo.UserPOJO;
import com.jspider.cardekho_case_study_restapp.response.UserResponse;
import com.jspider.cardekho_case_study_restapp.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping(path = "/createUser",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> createUser(@RequestBody UserPOJO pojo){
		
		UserPOJO user=service.createUser(pojo);
		//success
		if (user != null) {
			return new ResponseEntity<UserResponse>(new UserResponse("Account Created Successfully", user),HttpStatus.ACCEPTED);
			
		}
		//fail
		return new ResponseEntity<UserResponse>(new UserResponse("Account allready Exit, Procced to login", null),HttpStatus.NOT_ACCEPTABLE);
		
	}
	@CrossOrigin
	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> login(@RequestBody UserPOJO pojo){
		
		UserPOJO user=service.login(pojo);
		
		//success
		if (user != null) {
			return new ResponseEntity<UserResponse>(new UserResponse("Login successfully ", user),HttpStatus.OK);
			
		}
		
		return new ResponseEntity<UserResponse>(new UserResponse("Login failed , Please check your Username And Password",null),HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping(path = "forgotpassword",consumes = MediaType.APPLICATION_JSON_VALUE,
										produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> forgotPassword(@RequestBody UserPOJO pojo){
		
		
		UserPOJO user=service.changepassword(pojo);
		
		if (user !=null) {
			
			return new ResponseEntity<UserResponse>(new UserResponse("Password change !!", user),HttpStatus.OK);
			
		}
		return new ResponseEntity<UserResponse>(new UserResponse("User Deteils  not match Try again !!!",null),HttpStatus.NOT_ACCEPTABLE) ;
	}
}













