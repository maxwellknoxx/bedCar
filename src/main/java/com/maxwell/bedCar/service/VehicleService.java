package com.maxwell.bedCar.service;

import java.util.List;

import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.model.VehicleModel;

public interface VehicleService {

	List<VehicleModel> findAll();

	VehicleModel findById(Long id);

	VehicleModel addVehicle(VehicleEntity vehicle);

	VehicleModel updateVehicle(VehicleEntity vehicle);

	Boolean removeVehicle(Long id);

}