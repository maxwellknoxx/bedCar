package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.OwnerEntity;
import com.maxwell.bedCar.exception.ResourceNotFoundException;
import com.maxwell.bedCar.model.OwnerModel;
import com.maxwell.bedCar.repository.OwnerRepository;
import com.maxwell.bedCar.service.OwnerService;
import com.maxwell.bedCar.util.OwnerMapper;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerRepository repository;

	@Override
	public List<OwnerModel> findAll() {
		return OwnerMapper.entitiesToModels(repository.findAll());
	}

	@Override
	public OwnerModel findById(Long id) {
		OwnerModel ownerModel = OwnerMapper.entityToModel(repository.findById(id).orElseThrow());
		if (ownerModel == null) {
			throw new ResourceNotFoundException("Owner " + id + " does not exist");
		}
		return ownerModel;
	}

	public OwnerEntity findEntityById(Long id) {
		OwnerEntity entity = repository.findById(id).orElseThrow();
		if (entity == null) {
			throw new ResourceNotFoundException("Owner " + id + " does not exist");
		}
		return entity;
	}

	@Override
	public OwnerModel addOwner(OwnerEntity owner) {
		try {
			return OwnerMapper.entityToModel(repository.save(owner));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> add owner -> " + e.getMessage());
		}
	}

	@Override
	public OwnerModel updateOwner(OwnerEntity owner) {
		try {
			return OwnerMapper.entityToModel(repository.save(owner));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> update owner -> " + e.getMessage());
		}
	}

	@Override
	public void removeOwner(Long id) {
		OwnerModel ownerModel = OwnerMapper.entityToModel(repository.findById(id).orElseThrow());
		if (ownerModel == null) {
			throw new ResourceNotFoundException("Owner " + id + " does not exist");
		}
		repository.deleteById(id);
	}

}