package com.jspider.cardekho_case_study_restapp.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.cardekho_case_study_restapp.pojo.CarPOJO;
import com.jspider.cardekho_case_study_restapp.response.CarResponse;
import com.jspider.cardekho_case_study_restapp.service.CarService;



@CrossOrigin("*")
@RestController
public class CarController {
	@Autowired
	private CarService service;
	
	
	
	@PostMapping(path = "/addcar",consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> addCar(@RequestBody CarPOJO pojo){
		
		 CarPOJO car=service.addCar(pojo);
		 //success flow 
		 if (car != null) {
			return new ResponseEntity<CarResponse>(new CarResponse("Car Details add successfully", car, null),HttpStatus.ACCEPTED);
		}
		 ///FAILURE flow
		return new ResponseEntity<CarResponse>(new CarResponse("Car Detail allready exists !!!", car, null),HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@DeleteMapping(path = "/remove/{id}" ,produces  = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> removeCar(@PathVariable int id){
		
		CarPOJO car= service.removeCar(id);
		if (car != null) {
			return new ResponseEntity<CarResponse>(new CarResponse("Car data remove where id as "+id+"", car, null),HttpStatus.OK);
		}
		return new ResponseEntity<CarResponse>(new CarResponse("Car data not removed ",null, null),HttpStatus.BAD_REQUEST);
	}
	
	

	@PutMapping(path = "/updataCar" ,consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> updateCarDitails(@RequestBody CarPOJO pojo){
		
		CarPOJO car=service.updateCar(pojo);
		
		if (car != null) {
			return new ResponseEntity<CarResponse>(new CarResponse("Car Ditails update ...", car, null),HttpStatus.ACCEPTED);
			
		}
		return new ResponseEntity<CarResponse>(new CarResponse("Car Ditails update ...", car, null),HttpStatus.NOT_ACCEPTABLE);
	}
	

	
	@GetMapping(path="/searchCar/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> searchCarById(@PathVariable int id){
		
		CarPOJO car=service.searchCar(id);
		
		// success flow
		if(car != null) {
			return new ResponseEntity<CarResponse> (new CarResponse("Car data found successfully ", car, null),HttpStatus.FOUND);
			
		}
		//failure flow
		return new ResponseEntity<CarResponse>(new CarResponse("Car Data not Found !!!", null, null),HttpStatus.NOT_FOUND);
	}
	

	@GetMapping(path = "/searchAllCar",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> searchAllCar(){
		List<CarPOJO> cars=service.searchAllCar();
		
		// success flow 
		if (cars.isEmpty()==false) {
			return new ResponseEntity<CarResponse>(new CarResponse("Car data found", null, cars),HttpStatus.FOUND);
		}
		return new ResponseEntity<CarResponse>(new CarResponse("Car data not found ", null, null),HttpStatus.NOT_FOUND);
	}
	
	
	
	@PostMapping(path = "/searchCarByName" ,consumes = MediaType.APPLICATION_JSON_VALUE,
						produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> searchCarByName( @RequestBody CarPOJO pojo){
		
		List<CarPOJO> cars=service.searchCarByName(pojo.getCarName());
		System.out.println(pojo.getCarName());
		
		if (cars.isEmpty()==false) {
			
			return new ResponseEntity<CarResponse>(new CarResponse("Car data found ",null, cars),HttpStatus.FOUND);
		}
		return new ResponseEntity<CarResponse>(new CarResponse("Car data not found which name as "+pojo.getCarName()+"", null, null),HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping(path = "/searchCarByBrand/{brand}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> searchCarByBrand(@PathVariable String brand){
		
		List<CarPOJO> cars=service.searchCarBybrand(brand);
		
		if (cars.isEmpty()==false) {
			return new ResponseEntity<CarResponse>(new CarResponse("car data found", null, cars),HttpStatus.FOUND);
		}
		return new ResponseEntity<CarResponse>(new CarResponse("car data not found", null, null),HttpStatus.NOT_FOUND);
		
	}

	
	
	@PostMapping(path = "/searchCarByFuelType/{fuelType}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> searchCarByFuelType(@PathVariable String fuelType){
		
		List<CarPOJO> cars= service.searchCarByFuelType(fuelType);
		if (cars.isEmpty()==false) {
			return new ResponseEntity<CarResponse> (new CarResponse("Car data found where fuelType as "+fuelType, null, cars),HttpStatus.FOUND);
		
		}
		return new ResponseEntity<CarResponse> (new CarResponse("Car data not found where fuelType as "+fuelType, null, null),HttpStatus.NOT_FOUND);

		
	}
		
	@GetMapping(path ="/idrange",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> searchCarByIdRange(@RequestParam int min, @RequestParam int max){
		
		List<CarPOJO> cars=service.searchCarByIdRange(min,max);
		
		if (cars.isEmpty()==false) {
			return new ResponseEntity<CarResponse>
								(new CarResponse("car data found", null, cars),HttpStatus.OK);
		}
		
		return new ResponseEntity<CarResponse>
		(new CarResponse("car data not found", null, cars),HttpStatus.NOT_FOUND);
		
		
	
	}
	@PostMapping(path="/searchCarBetweenPriceRange" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponse> findCarinPriceRange(@RequestParam double minPrice,@RequestParam double maxPrice){
		
		List<CarPOJO>cars=service.findcarBetweenPrice(minPrice,maxPrice);
		
		if (cars.isEmpty()==false) {
			return new ResponseEntity<CarResponse>
								(new CarResponse("car data found", null, cars),HttpStatus.OK);
		}
		
		return new ResponseEntity<CarResponse>
		(new CarResponse("car data not found", null, null),HttpStatus.NOT_FOUND);
		
		
		
	}
	}

	
	
	
	










