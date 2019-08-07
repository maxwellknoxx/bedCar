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
		return VehicleMapper.entitiesToModelList(repository.findAll());
	}

	@Override
	public List<VehicleModel> findByPlate(String plate) {
		return VehicleMapper.entitiesToModelList(repository.findByPlate(plate));
	}

	@Override
	public VehicleModel findById(Long id) {
		return VehicleMapper.entityToModel(repository.findById(id).orElseThrow());
	}

	@Override
	public VehicleModel register(VehicleEntity entity) {
		return VehicleMapper.entityToModel(repository.save(entity));
	}

	@Override
	public VehicleModel update(VehicleEntity entity) {
		return VehicleMapper.entityToModel(repository.save(entity));
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
