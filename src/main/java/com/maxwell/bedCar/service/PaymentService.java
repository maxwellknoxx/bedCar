package com.maxwell.bedCar.service;

import java.util.List;

import com.maxwell.bedCar.entity.PaymentEntity;
import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.model.PaymentModel;

public interface PaymentService {

	List<PaymentModel> findAll();

	PaymentModel findById(Long id);

	PaymentModel register(PaymentEntity entity);
	
	List<PaymentModel> findByVehicle(VehicleEntity vehicle);

}
