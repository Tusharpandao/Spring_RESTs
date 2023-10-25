package com.jspiders.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrest.pojo.StudentPOJO;
import com.jspiders.springrest.response.StudentResponse;
import com.jspiders.springrest.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private  StudentService service;
	
	
	@CrossOrigin
	@PostMapping(path = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> addStudent(@RequestBody StudentPOJO pojo) {
		StudentPOJO student = service.addStudent(pojo);
		//SUCCESS
		if (student != null) {
			return new ResponseEntity<StudentResponse>(new StudentResponse("Data added successfully. ", student, null), HttpStatus.ACCEPTED);
		}
		//FAILURE
		return new ResponseEntity<StudentResponse>(new StudentResponse("Data not added. ", null, null), HttpStatus.ACCEPTED);
	}
//
//	@GetMapping(path = "/search",
//			consumes = MediaType.APPLICATION_JSON_VALUE,
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<StudentResponse> searchStudent(@RequestBody StudentPOJO pojo) {
//		
//		StudentPOJO student =service.searchStudent(pojo.getId());
//		
//		if(student != null) {
//			return new ResponseEntity<StudentResponse>(new StudentResponse("Data found Successfully", student, null),HttpStatus.FOUND);
//			
//		}
//		return new ResponseEntity<StudentResponse>(new StudentResponse("Data  Not found ", null, null),HttpStatus.NOT_FOUND);
//	}
//							OR
	@CrossOrigin
	@GetMapping(path = "/search/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> searchStudent(@PathVariable int id) {
	
	StudentPOJO student =service.searchStudent(id);
		
		if(student != null) {
			return new ResponseEntity<StudentResponse>(new StudentResponse("Data found Successfully", student, null),HttpStatus.FOUND);
			
		}
		return new ResponseEntity<StudentResponse>(new StudentResponse("Data  Not found ", null, null),HttpStatus.NOT_FOUND);
	}
	@CrossOrigin
	@GetMapping(path = "/searchAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> searchAllStudents(){
		List<StudentPOJO> students=service.searchAllStudent();
		
		if(students.isEmpty() == false) {
			return new ResponseEntity<StudentResponse> (new StudentResponse("Data found ",null,students),HttpStatus.FOUND);
			
			
		}
		
		return new ResponseEntity<StudentResponse> (new StudentResponse("Data  not found ",null,null),HttpStatus.NOT_FOUND);
	}
	@CrossOrigin
	@DeleteMapping(path = "/remove/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse>  removeStudent(@PathVariable int id){
		StudentPOJO student=service.removeStudent(id);
		
		if(student != null) {
			return new ResponseEntity<StudentResponse> (new StudentResponse("Student data removed", student, null),HttpStatus.OK);
			
		}
		
		return new ResponseEntity<StudentResponse> (new StudentResponse("Student data not found !!!", null, null),HttpStatus.BAD_REQUEST);
	}
	@CrossOrigin
	@PutMapping(path = "/update",consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> updateStudent(@RequestBody StudentPOJO pojo){
		StudentPOJO student=service.updateStudent(pojo);
		
		if (student != null) {
			return new ResponseEntity<StudentResponse>(new StudentResponse("Student data updated", student, null),HttpStatus.ACCEPTED);
			
		}
		
		return new ResponseEntity<StudentResponse>(new StudentResponse("Student data not updated", null, null),HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
}
		


