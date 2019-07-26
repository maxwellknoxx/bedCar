package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.UserEntity;
import com.maxwell.bedCar.exception.ResourceNotFoundException;
import com.maxwell.bedCar.model.UserModel;
import com.maxwell.bedCar.repository.UserRepository;
import com.maxwell.bedCar.service.UserService;
import com.maxwell.bedCar.util.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<UserModel> findAll() {
		return UserMapper.entitiesToModels(repository.findAll());
	}

	@Override
	public UserModel findById(Long id) {
		UserModel userModel = UserMapper.entityToModel(repository.findById(id).orElseThrow());
		if (userModel == null) {
			throw new ResourceNotFoundException("User " + id + " not found!");
		}
		return userModel;
	}

	@Override
	public UserModel create(UserEntity user) {
		try {
			return UserMapper.entityToModel(repository.save(user));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> create user -> " + e.getMessage());
		}
	}

	@Override
	public UserModel update(UserEntity user) {
		try {
			return UserMapper.entityToModel(repository.save(user));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> update user -> " + e.getMessage());
		}
	}

	@Override
	public void remove(Long id) {
		UserModel userModel = UserMapper.entityToModel(repository.findById(id).orElseThrow());
		if (userModel == null) {
			throw new ResourceNotFoundException("User " + id + " not found!");
		}
		repository.deleteById(id);
	}

}