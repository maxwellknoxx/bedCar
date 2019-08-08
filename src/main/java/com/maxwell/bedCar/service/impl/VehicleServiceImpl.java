package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.model.VehicleModel;
import com.maxwell.bedCar.repository.VehicleRepository;
import com.maxwell.bedCar.service.VehicleService;
import com.maxwell.bedCar.util.VehicleMapper;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository repository;

	@Override
	public List<VehicleModel> findAll() {
		List<VehicleEntity> entities = repository.findAll();
		if (entities.isEmpty()) {
			return null;
		}
		return VehicleMapper.entitiesToModelList(entities);
	}

	@Override
	public VehicleModel findByPlate(String plate) {
		VehicleEntity entity = repository.findByPlate(plate);
		if (entity == null) {
			return null;
		}
		return VehicleMapper.entityToModel(entity);
	}

	@Override
	public VehicleModel findById(Long id) {
		VehicleEntity entity = repository.findById(id).orElseThrow();
		if (entity == null) {
			return null;
		}
		return VehicleMapper.entityToModel(entity);
	}

	@Override
	public VehicleModel register(VehicleEntity entity) {
		VehicleEntity entityfromDB = repository.save(entity);
		if (entityfromDB == null) {
			return null;
		}
		return VehicleMapper.entityToModel(entityfromDB);
	}

	@Override
	public VehicleModel update(VehicleEntity entity) {
		VehicleEntity entityfromDB = repository.save(entity);
		if (entityfromDB == null) {
			return null;
		}
		return VehicleMapper.entityToModel(entityfromDB);
	}

	@Override
	public Boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
