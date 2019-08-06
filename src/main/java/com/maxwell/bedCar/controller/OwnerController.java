package com.maxwell.bedCar.controller;

import java.util.List;
import java.util.Objects;

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

	@PostMapping(path = "/api/v1/owner/owners")
	public ResponseEntity<?> insert(@Valid @RequestBody OwnerEntity entity, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		OwnerModel model = service.addOwner(entity);

		return new ResponseEntity<OwnerModel>(model, HttpStatus.CREATED);
	}

	@GetMapping(path = "/api/v1/owner/owners/{id}")
	public ResponseEntity<?> get(@Valid @PathVariable("id") Long id) {
		OwnerModel model = service.findById(id);

		return new ResponseEntity<OwnerModel>(model, HttpStatus.OK);
	}

	@GetMapping(path = "/api/v1/owner/owners")
	public ResponseEntity<?> findAll() {
		List<OwnerModel> list = service.findAll();

		return new ResponseEntity<List<OwnerModel>>(list, HttpStatus.OK);
	}

	@PutMapping(path = "/api/v1/owner/owners")
	public ResponseEntity<?> update(@Valid @RequestBody OwnerEntity entity) {
		OwnerModel model = service.updateOwner(entity);
		if (Objects.isNull(model)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<OwnerModel>(model, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/api/v1/owner/owners/{id}")
	public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id) {
		service.removeOwner(id);
		return new ResponseEntity<String>("Owner " + id + " deleted", HttpStatus.OK);
	}

}
