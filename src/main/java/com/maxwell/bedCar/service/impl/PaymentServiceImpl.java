package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.PaymentEntity;
import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.model.PaymentModel;
import com.maxwell.bedCar.repository.PaymentRepository;
import com.maxwell.bedCar.service.PaymentService;
import com.maxwell.bedCar.util.PaymentMapper;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository repository;

	@Override
	public List<PaymentModel> findAll() {
		List<PaymentEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return PaymentMapper.entitiesToModelList(list);
	}

	@Override
	public PaymentModel findById(Long id) {
		PaymentEntity entityFromDB = repository.findById(id).orElse(null);
		if (entityFromDB == null) {
			return null;
		}
		return PaymentMapper.entityToModel(entityFromDB);
	}

	@Override
	public PaymentModel register(PaymentEntity entity) {
		PaymentEntity entityFromDB = repository.save(entity);
		if (entityFromDB == null) {
			return null;
		}
		return PaymentMapper.entityToModel(entityFromDB);
	}

	@Override
	public List<PaymentModel> findByVehicle(VehicleEntity vehicle) {
		List<PaymentEntity> list = repository.findByVehicle(vehicle);
		if (list.isEmpty()) {
			return null;
		}
		return PaymentMapper.entitiesToModelList(list);
	}
	
	public List<PaymentModel> findByVehiclePlate(String plate) {
		List<PaymentEntity> list = repository.findByVehiclePlate(plate);
		if (list.isEmpty()) {
			return null;
		}
		return PaymentMapper.entitiesToModelList(list);
	}

	@Override
	public Long countByPaidValue(String paidValue) {
		return repository.countByPaidValue(paidValue);
	}

}
