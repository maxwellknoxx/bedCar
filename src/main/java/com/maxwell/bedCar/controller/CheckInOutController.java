package com.maxwell.bedCar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.bedCar.entity.CheckInOutEntity;
import com.maxwell.bedCar.model.CheckInOutModel;
import com.maxwell.bedCar.service.impl.CheckInOutServiceImpl;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;
import com.maxwell.bedCar.util.Time;

@RestController
@CrossOrigin("*")
public class CheckInOutController {

	@Autowired
	private CheckInOutServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * Check in the single car
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/checkInOut/checksIn")
	public ResponseEntity<?> checkIn(@Valid @RequestBody CheckInOutEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}
		
		entity.setCheckInDate(Time.getCurrentDate());

		entity.setCheckInHour(Time.hourNow());

		CheckInOutModel model = service.register(entity);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	/**
	 * Check in the single car
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PostMapping(path = "/api/v1/checkInOut/checksOut")
	public ResponseEntity<?> checkOut(@Valid @RequestBody CheckInOutEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		String totalToPay = Time.calculate(entity.getCheckInHour(), entity.getCheckOutHour());
		
		CheckInOutModel model = service.register(entity);
		
		model.setValue(totalToPay);
		
		String totalHours = Time.calculateHours(entity.getCheckInHour(), entity.getCheckOutHour());
		
		model.setTotalHours(totalHours);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	/**
	 * Check in the single car
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PostMapping(path = "/api/v1/checkInOut/payments")
	public ResponseEntity<?> registerPayment(@Valid @RequestBody CheckInOutEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		CheckInOutModel model = service.register(entity);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	@PutMapping(path = "/api/v1/checkInOut/checksInOut")
	public ResponseEntity<?> update(@Valid @RequestBody CheckInOutEntity entity) {
		CheckInOutModel model = service.update(entity);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	@GetMapping(path = "/api/v1/checkInOut/checksInOut")
	public ResponseEntity<?> findAll() {
		List<CheckInOutModel> list = service.findAll();

		return new ResponseEntity<List<CheckInOutModel>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/api/v1/checkInOut/checksInOut/{id}")
	public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id) {
		CheckInOutModel model = service.findById(id);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.OK);
	}

	@GetMapping(path = "/api/v1/checkInOut/checksInOut/{plate}")
	public ResponseEntity<?> findByPlate(@Valid @PathVariable("plate") String plate) {
		CheckInOutModel model = service.findByPlate(plate);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.OK);
	}

}
