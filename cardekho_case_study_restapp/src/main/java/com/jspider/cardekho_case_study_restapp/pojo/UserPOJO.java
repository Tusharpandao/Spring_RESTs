package com.jspider.cardekho_case_study_restapp.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserPOJO {
	@Id
	private String username;
	private String name;
	private String phone;
	private String email;
	private String password;
}
