package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.CheckInOutEntity;
import com.maxwell.bedCar.exception.ResourceNotFoundException;
import com.maxwell.bedCar.model.CheckInOutModel;
import com.maxwell.bedCar.repository.CheckInOutRepository;
import com.maxwell.bedCar.service.CheckInOutService;
import com.maxwell.bedCar.util.CheckInOutMapper;

@Service
public class CheckInOutServiceImpl implements CheckInOutService {

	@Autowired
	private CheckInOutRepository repository;

	@Override
	public List<CheckInOutModel> findAll() {
		return CheckInOutMapper.entitiesToModelList(repository.findAll());
	}

	@Override
	public CheckInOutModel findById(Long id) {
		return CheckInOutMapper.entityToModel(repository.findById(id).orElseThrow());
	}

	@Override
	public CheckInOutModel findByCarPlate(String carPlate) {
		return CheckInOutMapper.entityToModel(repository.findByCarPlate(carPlate));
	}

	@Override
	public CheckInOutModel add(CheckInOutEntity entity) {
		try {
			return CheckInOutMapper.entityToModel(repository.save(entity));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> add -> " + e.getMessage());
		}
	}

	@Override
	public CheckInOutModel update(CheckInOutEntity entity) {
		try {
			return CheckInOutMapper.entityToModel(repository.save(entity));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> update -> " + e.getMessage());
		}
	}

}
