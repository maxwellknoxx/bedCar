package com.maxwell.bedCar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.model.VehicleModel;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;
import com.maxwell.bedCar.service.impl.VehicleServiceImpl;
import com.maxwell.bedCar.util.Time;

@RestController
@CrossOrigin("*")
public class VehicleController {

	@Autowired
	private VehicleServiceImpl service;

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
		
		entity.setSubscriptionDate(Time.getAtualDate());
		
		VehicleModel model = service.register(entity);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<VehicleModel>(model, HttpStatus.OK);
	}

}
