package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.UserEntity;
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
		List<UserEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return UserMapper.entitiesToModels(list);
	}

	@Override
	public UserModel findById(Long id) {
		UserEntity entity = repository.findById(id).orElse(null);
		if (entity == null) {
			return null;
		}
		return UserMapper.entityToModel(entity);
	}

	public UserEntity findEntityById(Long id) {
		UserEntity entity = repository.findById(id).orElse(null);
		if (entity == null) {
			return null;
		}
		return repository.findById(id).orElseThrow();
	}

	@Override
	public Boolean create(UserEntity user) {
		UserEntity entity = repository.save(user);
		if (entity == null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean update(UserEntity user) {
		UserEntity entity = repository.save(user);
		if (entity == null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean remove(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}