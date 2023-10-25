package com.jspider.cardekho_case_study_restapp.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jspider.cardekho_case_study_restapp.pojo.UserPOJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
	private String msg;
	private UserPOJO user;
	
}
