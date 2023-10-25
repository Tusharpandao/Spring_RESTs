package com.jspider.cardekho_case_study_restapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspider.cardekho_case_study_restapp.pojo.CarPOJO;

@Repository	
public class CarRepository {

	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;
	
	private static void openConnection() {
		
		factory = Persistence.createEntityManagerFactory("case_study");
		manager= factory.createEntityManager();
		transaction=manager.getTransaction();
	}
	private static void closeConnection() {
		if (factory != null) {
			
			factory.close();
		}
		if (manager != null ) {
			manager.close();
		}
		if(transaction != null) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
		}
	}
	public CarPOJO addCar(CarPOJO pojo) {
		openConnection();
		transaction.begin();
//		CarPOJO car=manager.find(CarPOJO.class, pojo.getCarName());
//		if (car == null) {
			manager.persist(pojo);
			transaction.commit();
			closeConnection();
			return pojo;
//		}
//		transaction.commit();
//		closeConnection();
//		return null;
	}
	public CarPOJO searchCar(int id) {
		openConnection();
		transaction.begin();
		
		CarPOJO car=manager.find(CarPOJO.class, id);
		
		transaction.commit();
		closeConnection();
		return car;
	}
	public List<CarPOJO> searchAllCar() {
		
		openConnection();
		transaction.begin();
		
		String jpql="from CarPOJO";
		
		query=manager.createQuery(jpql);
		List<CarPOJO> cars=query.getResultList();
		
		transaction.commit();
		closeConnection();
		return cars;
	}
	public List<CarPOJO> searchCarByName(String carName) {

		openConnection();
		transaction.begin();
		
		String jpql="from CarPOJO where carName = '"+carName+"'";
		
		query=manager.createQuery(jpql);
		
		List<CarPOJO> cars=query.getResultList();
		
		transaction.commit();
		closeConnection();
		return cars;
	}
	public CarPOJO removeCar(int id) {

		openConnection();
		transaction.begin();
		
		CarPOJO pojo=manager.find(CarPOJO.class, id);
		
		if (pojo != null) {
			manager.remove(pojo);
			transaction.commit();
			closeConnection();
			return pojo;
			
			
		}
		transaction.commit();
		closeConnection();
		return null;
	}
	public List<CarPOJO> searchCarByBrand(String brand) {

		openConnection();
		transaction.begin();
		
		String jpql= "from CarPOJO where brandName = '"+brand+"'";
		
		query=manager.createQuery(jpql);
		
		List<CarPOJO> cars=query.getResultList();
		
		
		
		transaction.commit();
		closeConnection();
		
		return cars;
	}
	public List<CarPOJO> searchCarByFuel(String fuelType) {
		
		openConnection();
		transaction.begin();
		
		String jpql= "from CarPOJO where fuelType = '"+fuelType+"'";
		
		query=manager.createQuery(jpql);
		
		List<CarPOJO> cars=query.getResultList();
		
		
		
		transaction.commit();
		closeConnection();
		
		return cars;
	}
	public CarPOJO updateCar(CarPOJO pojo) {
		
		openConnection();
		transaction.begin();
		
		CarPOJO car=manager.find(CarPOJO.class, pojo.getId());
		
		if (car != null) {
			
			car.setCarName(pojo.getCarName());
			car.setBrandName(pojo.getBrandName());
			car.setFuelType(pojo.getFuelType());
			car.setPrice(pojo.getPrice());
			
			manager.persist(car);
			transaction.commit();
			closeConnection();
			return car;
		}
		transaction.commit();
		closeConnection();
		return null;
	}
	public List<CarPOJO> findCarByIdRange(int min, int max) {
		
		openConnection();
		transaction.begin();
		
		String jpql="from CarPOJO  where id between "+min+" and " +max;
		
		query=manager.createQuery(jpql);
		
		List<CarPOJO> cars=query.getResultList();
		
		transaction.commit();
		closeConnection();
		return cars;
	}
	public List<CarPOJO> findCarBetweenPrice(double minPrice, double maxPrice) {
		
		openConnection();
		transaction.begin();
		
		String jpql="from CarPOJO  where price between "+minPrice+" and " +maxPrice;
		
		query=manager.createQuery(jpql);
		
		List<CarPOJO> cars=query.getResultList();
		
		transaction.commit();
		closeConnection();
		return cars;
	}
}
