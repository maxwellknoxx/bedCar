package com.maxwell.bedCar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.model.VehicleModel;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;
import com.maxwell.bedCar.service.impl.SpaceServiceImpl;
import com.maxwell.bedCar.service.impl.VehicleServiceImpl;
import com.maxwell.bedCar.util.DateAndTime;
import com.maxwell.bedCar.util.VehicleMapper;

@RestController
@CrossOrigin("*")
public class VehicleController {

	@Autowired
	private VehicleServiceImpl service;

	@Autowired
	private SpaceServiceImpl spaceService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PostMapping(path = "/api/v1/vehicle/vehicles")
	public ResponseEntity<?> register(@Valid @RequestBody VehicleEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		entity = setDates(entity);

		VehicleModel model = service.register(entity);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		spaceService.updateSpace(entity.getSpace());

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PutMapping(path = "/api/v1/vehicle/vehicles")
	public ResponseEntity<?> update(@Valid @RequestBody VehicleEntity entity) {

		entity = setDates(entity);
		entity.getSpace().setBusy(true);

		VehicleModel model = service.update(entity);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		spaceService.updateSpace(entity.getSpace());

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = "/api/v1/vehicle/vehicles/{id}")
	public ResponseEntity<?> deleteById(@Valid @PathVariable("id") Long id) {
		if(service.delete(id)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@GetMapping(path = "/api/v1/vehicle/vehicles")
	public ResponseEntity<?> findAll(){
		List<VehicleModel> list = service.findAll();
		if (list == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<VehicleModel>>(list, HttpStatus.OK);
	}
	
	@GetMapping(path = "/api/v1/vehicle/vehiclesByPlate/{plate}")
	public ResponseEntity<?> findByPlate(@Valid @PathVariable("plate") String plate){
		VehicleModel model = service.findByPlate(plate);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		
		return new ResponseEntity<VehicleModel>(model, HttpStatus.OK);
	}
	
	@GetMapping(path = "/api/v1/vehicle/VehiclesByPlan/{plan}") 
	public ResponseEntity<?> findByPlan(@Valid @PathVariable("plan") String plan){
		List<VehicleModel> list = service.findByPlan(plan);
		if(list == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<List<VehicleModel>>(list, HttpStatus.OK);
	}
	
	@GetMapping(path = "/api/v1/vehicle/TotalVehiclesByPlan/{plan}") 
	public ResponseEntity<?> countByPlan(@Valid @PathVariable("plan") String plan){
		Long total = service.countByPlan(plan);
		
		return new ResponseEntity<Long>(total, HttpStatus.OK);
	}

	public VehicleEntity setDates(VehicleEntity entity) {
		String subscriptionDate = DateAndTime.getCurrentDate();
		Integer days = VehicleMapper.getPlanDays(entity.getPlan());
		
		entity.setSubscriptionDate(subscriptionDate);
		entity.setDueDate(DateAndTime.getDueDate(subscriptionDate, days));
		return entity;
	}

}
