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
import com.maxwell.bedCar.service.impl.SpaceServiceImpl;
import com.maxwell.bedCar.util.DateAndTime;

@RestController
@CrossOrigin("*")
public class CheckInOutController {

	@Autowired
	private CheckInOutServiceImpl service;

	@Autowired
	private SpaceServiceImpl spaceService;

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

		entity.setCheckInDate(DateAndTime.getCurrentDate());

		entity.setCheckInHour(DateAndTime.getCurrentHour());

		entity.setStatus(false);

		CheckInOutModel model = service.register(entity);

		spaceService.updateSpaceById(entity.getSpaceId(), true);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/checkInOut/checksOut")
	public ResponseEntity<?> checkOut(@Valid @RequestBody CheckInOutEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		entity.setCheckOutHour(DateAndTime.getCurrentHour());

		entity.setStatus(false);

		String totalToPay = DateAndTime.calculate(entity.getCheckInHour(), entity.getCheckOutHour());

		String totalHours = DateAndTime.calculateHours(entity.getCheckInHour(), entity.getCheckOutHour());

		entity.setTotalHours(totalHours);

		CheckInOutModel model = service.register(entity);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		model.setValue(totalToPay);

		spaceService.updateSpaceById(entity.getSpaceId(), false);

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/checkInOut/payments")
	public ResponseEntity<?> registerPayment(@Valid @RequestBody CheckInOutEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		if (!entity.getValue().isBlank()) {
			entity.setStatus(true);

			CheckInOutModel model = service.register(entity);
			if (model == null) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}

			return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
		}

		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/checkInOut/checksInOut")
	public ResponseEntity<?> update(@Valid @RequestBody CheckInOutEntity entity) {
		CheckInOutModel model = service.update(entity);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/checkInOut/checksInOut")
	public ResponseEntity<?> findAll() {
		List<CheckInOutModel> list = service.findAll();

		return new ResponseEntity<List<CheckInOutModel>>(list, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/checkInOut/checksInOut/{id}")
	public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id) {
		CheckInOutModel model = service.findById(id);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/checkInOut/findByPlate/{plate}")
	public ResponseEntity<?> findByPlate(@Valid @PathVariable("plate") String plate) {
		CheckInOutModel model = service.findByPlateAndStatus(plate, false);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<CheckInOutModel>(model, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/checkInOut/totalCheckInOut")
	public ResponseEntity<?> count() {
		long total = service.count();

		return new ResponseEntity<Long>(total, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/checkInOut/paidValues")
	public ResponseEntity<?> getPaidValues() {
		List<CheckInOutModel> list = service.findAll();
		if (list == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		String total = countPaidValue(list);

		return new ResponseEntity<String>(total, HttpStatus.OK);
	}

	public String countPaidValue(List<CheckInOutModel> list) {
		Double total = (double) 0;
		for (CheckInOutModel entity : list) {
			total = total + Double.parseDouble(entity.getValue().replace("€", ""));
		}
		return String.format("%.2f", total);
	}

}
