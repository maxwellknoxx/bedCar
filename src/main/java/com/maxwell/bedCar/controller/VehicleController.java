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

import com.maxwell.bedCar.entity.SpaceEntity;
import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.exception.ResourceNotFoundException;
import com.maxwell.bedCar.model.VehicleModel;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;
import com.maxwell.bedCar.service.impl.VehicleServiceImpl;

@RestController
@CrossOrigin("*")
public class VehicleController {

	@Autowired
	private VehicleServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@GetMapping(path = "/api/v1/vehicle/vehicles")
	public ResponseEntity<?> findAll() {
		List<VehicleModel> list = service.findAll();
		return new ResponseEntity<List<VehicleModel>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/api/v1/vehicle/vehicles/{id}")
	public ResponseEntity<?> get(@Valid @PathVariable("id") Long id) {
		VehicleModel model = service.findById(id);

		return new ResponseEntity<VehicleModel>(model, HttpStatus.OK);
	}

	@PostMapping(path = "/api/v1/vehicle/vehicles")
	public ResponseEntity<?> add(@Valid @RequestBody VehicleEntity entity, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		VehicleModel model = service.addVehicle(entity);

		return new ResponseEntity<VehicleModel>(model, HttpStatus.CREATED);
	}

	@PutMapping(path = "/api/v1/vehicle/vehicles")
	public ResponseEntity<?> update(@Valid @RequestBody VehicleEntity entity) {
		VehicleModel model = service.updateVehicle(entity);
		if (Objects.isNull(model)) {
			throw new ResourceNotFoundException("Something went wrong -> update -> " + entity.getCarPlate());
		}
		return new ResponseEntity<VehicleModel>(model, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/api/v1/vehicle/vehicles/{id}")
	public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id) {
		VehicleEntity vehicle = service.findEntityById(id);
		SpaceEntity space = vehicle.getSpace();

		service.removeVehicle(id);
		updateSpace(space);

		return new ResponseEntity<String>("Vehicle " + vehicle.getCarPlate() + " has been removed", HttpStatus.OK);
	}

	public void updateSpace(SpaceEntity entity) {
		SpaceController spaceController = new SpaceController();
		entity.setBusy(false);
		spaceController.update(entity);
	}

}
