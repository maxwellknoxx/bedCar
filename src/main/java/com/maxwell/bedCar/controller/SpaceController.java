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
import com.maxwell.bedCar.exception.ResourceNotFoundException;
import com.maxwell.bedCar.model.SpaceModel;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;
import com.maxwell.bedCar.service.impl.SpaceServiceImpl;

@RestController
@CrossOrigin("*")
public class SpaceController {

	@Autowired
	private SpaceServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@GetMapping(path = "/api/v1/space/spaces")
	public ResponseEntity<?> findAll() {
		List<SpaceModel> list = service.findAll();

		return new ResponseEntity<List<SpaceModel>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/api/v1/space/spaces/{id}")
	public ResponseEntity<?> get(@Valid @PathVariable("id") Long id) {
		SpaceModel model = service.findById(id);

		return new ResponseEntity<SpaceModel>(model, HttpStatus.OK);
	}

	@PostMapping(path = "/api/v1/space/spaces")
	public ResponseEntity<?> add(@Valid @RequestBody SpaceEntity entity, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		SpaceModel model = service.add(entity);

		return new ResponseEntity<SpaceModel>(model, HttpStatus.CREATED);
	}

	@PutMapping(path = "/api/v1/space/spaces")
	public ResponseEntity<?> update(@Valid @RequestBody SpaceEntity entity) {
		SpaceModel model = service.update(entity);
		if (Objects.isNull(model)) {
			throw new ResourceNotFoundException("Something went wrong -> update -> " + entity.getId());
		}
		return new ResponseEntity<SpaceModel>(model, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/api/v1/space/spaces/{id}")
	public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id) {
		service.remove(id);

		return new ResponseEntity<String>("Space " + id + " has been removed", HttpStatus.OK);
	}

}
