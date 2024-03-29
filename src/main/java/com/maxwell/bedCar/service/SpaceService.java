package com.maxwell.bedCar.service;

import java.util.List;

import com.maxwell.bedCar.entity.SpaceEntity;
import com.maxwell.bedCar.model.SpaceModel;

public interface SpaceService {

	List<SpaceModel> findAll();

	SpaceModel findById(Long id);
	
	List<SpaceModel> findByBusy(Boolean status);

	SpaceModel add(SpaceEntity space);

	SpaceModel update(SpaceEntity space);

	Boolean remove(Long id);

}
