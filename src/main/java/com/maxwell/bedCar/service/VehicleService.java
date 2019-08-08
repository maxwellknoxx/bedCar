package com.maxwell.bedCar.service;

import java.util.List;

import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.model.VehicleModel;

public interface VehicleService {

	List<VehicleModel> findAll();

	VehicleModel findByPlate(String plate);

	VehicleModel findById(Long id);
	
	VehicleModel register(VehicleEntity entity);
	
	VehicleModel update(VehicleEntity entity);
	
	Boolean delete(Long id);

}
