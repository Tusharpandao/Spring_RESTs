package com.jspider.cardekho_case_study_restapp.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Repository;

import com.jspider.cardekho_case_study_restapp.pojo.UserPOJO;

@Repository
public class UserRepository {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	private static void openConnection() {
		factory=Persistence.createEntityManagerFactory("case_study");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
	}
	private static void closeConnection() {
		
		if (factory != null) {
			factory.close();
		}
		if (manager != null) {
			manager.close();
		}
		if (transaction != null) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}

	public UserPOJO createUser(UserPOJO pojo) {
		openConnection();
		transaction.begin();
		UserPOJO user=manager.find(UserPOJO.class, pojo.getUsername());
		// User Not Exists in database perform this operation
		if (user == null) {
			manager.persist(pojo);
			transaction.commit();
			closeConnection();
			return pojo;
			
		}
		
//		User  Exists in database perform this operation and return null value to called  method
		transaction.commit();
		closeConnection();
		return null;
	}
	public UserPOJO login(UserPOJO pojo) {
		openConnection();
		transaction.begin();
		
		UserPOJO user=manager.find(UserPOJO.class, pojo.getUsername());
		
		if (user != null) {
			if (user.getUsername().equals(pojo.getUsername()) && user.getPassword().equals(pojo.getPassword())) {
				transaction.commit();
				closeConnection();
				return user;
			}
		}
		
		
		transaction.commit();
		closeConnection();
		return null;
	}
	public UserPOJO changePassword(UserPOJO pojo) {
		openConnection();
		transaction.begin();
		
		UserPOJO user=manager.find(UserPOJO.class,pojo.getUsername());
		
		if (user != null) {
			if (user.getPhone().equals(pojo.getPhone()) && user.getEmail().equals(pojo.getEmail())) 
			{
				
				user.setPassword(pojo.getPassword());
				manager.persist(user);
				transaction.commit();
				closeConnection();
				return user;
			}
			
		}
		transaction.commit();
		closeConnection();
		return null;
	}

}














