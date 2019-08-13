package com.maxwell.bedCar.service;

import java.util.List;

import com.maxwell.bedCar.entity.UserEntity;
import com.maxwell.bedCar.model.UserModel;

public interface UserService {

	List<UserModel> findAll();

	UserModel findById(Long id);

	Boolean create(UserEntity user);

	Boolean update(UserEntity user);

	Boolean remove(Long id);

}