package com.maxwell.bedCar.service;

import java.util.List;

import com.maxwell.bedCar.entity.CheckInOutEntity;
import com.maxwell.bedCar.model.CheckInOutModel;

public interface CheckInOutService {
	
	List<CheckInOutModel> findAll();
	
	long count();

	CheckInOutModel findById(Long id);

	CheckInOutModel findByPlate(String Plate);
	
	CheckInOutModel register(CheckInOutEntity entity);
	
	CheckInOutModel update(CheckInOutEntity entity);
	

}
