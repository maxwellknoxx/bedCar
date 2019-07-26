package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.exception.ResourceNotFoundException;
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
		return VehicleMapper.entitiesToModels(repository.findAll());
	}

	@Override
	public VehicleModel findById(Long id) {
		VehicleModel vehicleModel = VehicleMapper.entityToModel(repository.findById(id).orElseThrow());
		if (vehicleModel == null) {
			throw new ResourceNotFoundException("Vehicle " + id + " does not exist");
		}
		return vehicleModel;
	}

	@Override
	public VehicleModel addVehicle(VehicleEntity vehicle) {
		try {
			return VehicleMapper.entityToModel(repository.save(vehicle));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> create vehicle ->" + e.getMessage());
		}
	}

	@Override
	public VehicleModel updateVehicle(VehicleEntity vehicle) {
		try {
			return VehicleMapper.entityToModel(repository.save(vehicle));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> update vehicle ->" + e.getMessage());
		}
	}

	@Override
	public void removeVehicle(Long id) {
		VehicleModel vehicleModel = VehicleMapper.entityToModel(repository.findById(id).orElseThrow());
		if (vehicleModel == null) {
			throw new ResourceNotFoundException("Vehicle " + id + " does not exist");
		}
		repository.deleteById(id);
	}

}