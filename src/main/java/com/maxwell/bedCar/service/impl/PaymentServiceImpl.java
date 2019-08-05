package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.PaymentEntity;
import com.maxwell.bedCar.exception.ResourceNotFoundException;
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
		return PaymentMapper.entitiesToModelList(repository.findAll());
	}

	@Override
	public PaymentModel findById(Long id) {
		return PaymentMapper.entityToModel(repository.findById(id).orElseThrow());
	}

	@Override
	public PaymentModel add(PaymentEntity entity) {
		try {
			return PaymentMapper.entityToModel(repository.save(entity));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> add ->" + e.getMessage());
		}
	}

	@Override
	public PaymentModel findByOwnerId(Long id) {
		return PaymentMapper.entityToModel(repository.findByOwnerId(id));
	}

}