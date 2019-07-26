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

import com.maxwell.bedCar.entity.OwnerEntity;
import com.maxwell.bedCar.model.OwnerModel;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;
import com.maxwell.bedCar.service.impl.OwnerServiceImpl;

@RestController
@CrossOrigin("*")
public class OwnerController {

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@Autowired
	private OwnerServiceImpl service;

	//TAMU AI 
	@PostMapping(path = "/api/v1/owner/owners")
	public ResponseEntity<?> insert(@Valid @RequestBody OwnerEntity request, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null)
			return errorMap;

		OwnerModel model = service.addOwner(request);
		
		return new ResponseEntity<OwnerModel>(model, HttpStatus.CREATED);
	}

}
