package com.maxwell.bedCar.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

		UserModel model = service.create(entity);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	/**
	 * 
	 * @param entity
	 * @param result
	 * @return
	 */
	@PutMapping(path = "/api/v1/user/users")
	public ResponseEntity<?> update(@Valid @RequestBody UserEntity entity) {
		String passwordEncoded = encoder.encode(entity.getPassword());

		entity.setPassword(passwordEncoded);
		UserModel model = service.update(entity);
		if (Objects.isNull(model)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

}