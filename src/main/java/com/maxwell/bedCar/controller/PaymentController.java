package com.maxwell.bedCar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.bedCar.entity.PaymentEntity;
import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.model.PaymentModel;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;
import com.maxwell.bedCar.service.impl.PaymentServiceImpl;
import com.maxwell.bedCar.util.DateAndTime;

@RestController
@CrossOrigin("*")
public class PaymentController {

	@Autowired
	private PaymentServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping(path = "/api/v1/payment/payments")
	public ResponseEntity<?> register(@Valid @RequestBody PaymentEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		entity.setPaymentDate(DateAndTime.getCurrentDate());

		if (service.register(entity) == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}

		List<PaymentModel> list = service.findByVehiclePlate(entity.getVehicle().getPlate());

		return new ResponseEntity<List<PaymentModel>>(list, HttpStatus.CREATED);
	}

	@GetMapping(path = "/api/v1/payment/payments")
	public ResponseEntity<?> findAll() {
		List<PaymentModel> list = service.findAll();

		return new ResponseEntity<List<PaymentModel>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/api/v1/payment/payments/{id}")
	public ResponseEntity<?> get(@Valid @PathVariable("id") Long id) {
		PaymentModel model = service.findById(id);

		return new ResponseEntity<PaymentModel>(model, HttpStatus.OK);
	}

	@PostMapping(path = "/api/v1/payment/findPaymentByVehiclePlate")
	public ResponseEntity<?> findPaymentByVehiclePlate(@Valid @RequestBody VehicleEntity entity) {
		List<PaymentModel> list = service.findByVehiclePlate(entity.getPlate());

		if (list == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<List<PaymentModel>>(list, HttpStatus.OK);
	}

}
