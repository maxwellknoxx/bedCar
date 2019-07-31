package com.maxwell.bedCar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.bedCar.entity.CheckInOutEntity;
import com.maxwell.bedCar.model.CheckInOutModel;
import com.maxwell.bedCar.service.impl.CheckInOutServiceImpl;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;

@RestController
@CrossOrigin("*")
public class CheckInOutController {

	@Autowired
	private CheckInOutServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping(path = "/api/v1/checkInOut/checksInOut")
	public ResponseEntity<?> add(@Valid @RequestBody CheckInOutEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		CheckInOutModel model = service.add(entity);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	@GetMapping(path = "/api/v1/checkInOut/checksInOut")
	public ResponseEntity<?> findAll() {
		List<CheckInOutModel> list = service.findAll();

		return new ResponseEntity<List<CheckInOutModel>>(list, HttpStatus.OK);
	}
}
