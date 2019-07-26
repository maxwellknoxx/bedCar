package com.maxwell.bedCar.service;

import java.util.List;

import com.maxwell.bedCar.entity.OwnerEntity;
import com.maxwell.bedCar.model.OwnerModel;

public interface OwnerService {

	List<OwnerModel> findAll();

	OwnerModel findById(Long id);

	OwnerModel addOwner(OwnerEntity owner);

	OwnerModel updateOwner(OwnerEntity owner);

	void removeOwner(Long id);

}