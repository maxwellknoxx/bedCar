package com.maxwell.bedCar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.bedCar.entity.UserEntity;
import com.maxwell.bedCar.model.UserModel;
import com.maxwell.bedCar.service.impl.MapValidationErrorService;
import com.maxwell.bedCar.service.impl.UserServiceImpl;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping(path = "/api/v1/user/users")
	public ResponseEntity<?> findAll() {
		List<UserModel> list = service.findAll();

		return new ResponseEntity<List<UserModel>>(list, HttpStatus.OK);
	}

	/**
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PostMapping(path = "/api/v1/user/users")
	public ResponseEntity<?> create(@Valid @RequestBody UserEntity entity, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null) {
			return errorMap;
		}

		String passwordEncoded = encoder.encode(entity.getPassword());

		entity.setPassword(passwordEncoded);

		if (!service.create(entity)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PutMapping(path = "/api/v1/user/users")
	public ResponseEntity<?> update(@Valid @RequestBody UserEntity entity) {
		UserEntity entityFromDB = new UserEntity();
		String passwordEncoded = "";

		if (entity.getPassword().equals("")) {
			entityFromDB = service.findEntityById(entity.getId());
			entity.setPassword(entityFromDB.getPassword());
		} else {
			passwordEncoded = encoder.encode(entity.getPassword());
			entity.setPassword(passwordEncoded);
		}

		if (!service.create(entity)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/api/v1/user/users/{id}")
	public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id) {
		UserModel model = service.findById(id);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<UserModel>(model, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = "/api/v1/user/users/{id}")
	public ResponseEntity<?> deleteById(@Valid @PathVariable("id") Long id) {
		if (service.remove(id)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

}
