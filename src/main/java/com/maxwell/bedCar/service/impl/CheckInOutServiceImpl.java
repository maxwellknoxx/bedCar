package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.CheckInOutEntity;
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
		List<CheckInOutEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return CheckInOutMapper.entitiesToModelList(list);
	}

	@Override
	public CheckInOutModel findById(Long id) {
		CheckInOutEntity entity = repository.findById(id).orElseThrow();
		if (entity == null) {
			return null;
		}
		return CheckInOutMapper.entityToModel(entity);
	}

	@Override
	public CheckInOutModel findByPlate(String carPlate) {
		CheckInOutEntity entity = repository.findByPlate(carPlate);
		if (entity == null) {
			return null;
		}
		return CheckInOutMapper.entityToModel(entity);
	}

	@Override
	public CheckInOutModel register(CheckInOutEntity entity) {
		CheckInOutEntity entityFromDB = repository.save(entity);
		if (entityFromDB == null) {
			return null;
		}
		return CheckInOutMapper.entityToModel(entityFromDB);
	}

	@Override
	public CheckInOutModel update(CheckInOutEntity entity) {
		CheckInOutEntity entityFromDB = repository.save(entity);
		if (entityFromDB == null) {
			return null;
		}
		return CheckInOutMapper.entityToModel(entityFromDB);
	}

}
