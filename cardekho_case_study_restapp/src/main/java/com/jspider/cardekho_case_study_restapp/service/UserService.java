package com.jspider.cardekho_case_study_restapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.cardekho_case_study_restapp.pojo.UserPOJO;
import com.jspider.cardekho_case_study_restapp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public UserPOJO createUser(UserPOJO pojo) {
		
		UserPOJO user=repository.createUser(pojo);
		return user;
	}

	public UserPOJO login(UserPOJO pojo) {
		UserPOJO user=repository.login(pojo);
		return user;
	}

	public UserPOJO changepassword(UserPOJO pojo) {
		UserPOJO user=repository.changePassword(pojo);
		return user;
	}
}
