package com.jspider.cardekho_case_study_restapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspider.cardekho_case_study_restapp.pojo.CarPOJO;
import com.jspider.cardekho_case_study_restapp.repository.CarRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository repository;

	public CarPOJO addCar(CarPOJO pojo) {
		CarPOJO car=repository.addCar(pojo);
		return car;
	}

	public CarPOJO searchCar(int id) {
		CarPOJO car=repository.searchCar(id);
		return car;
	}

	public List<CarPOJO> searchAllCar() {
		List<CarPOJO> cars=repository.searchAllCar();
		return cars;
	}

	public List<CarPOJO> searchCarByName(String carName) {
		List<CarPOJO> cars=repository.searchCarByName(carName);
		
		return cars;
	}

	public CarPOJO removeCar(int id) {

		CarPOJO car=repository.removeCar(id);
		return car;
	}

	public List<CarPOJO> searchCarBybrand(String brand) {
		List<CarPOJO> cars=repository.searchCarByBrand(brand);
		
		return cars;
	}

	public List<CarPOJO> searchCarByFuelType(String fuelType) {
		
		List<CarPOJO> cars=repository.searchCarByFuel(fuelType);
			return cars;
		
		
	}

	public CarPOJO updateCar(CarPOJO pojo) {

		CarPOJO car=repository.updateCar(pojo);
		return car;
	}

	public List<CarPOJO> searchCarByIdRange(int min, int max) {
		
		List<CarPOJO>cars=repository.findCarByIdRange(min ,max);
		return cars;
	}

	public List<CarPOJO> findcarBetweenPrice(double minPrice, double maxPrice) {
		List<CarPOJO> cars=repository.findCarBetweenPrice(minPrice,maxPrice);
		return cars;
	}
}









