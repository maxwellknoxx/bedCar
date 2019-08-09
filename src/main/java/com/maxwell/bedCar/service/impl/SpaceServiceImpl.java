package com.maxwell.bedCar.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.SpaceEntity;
import com.maxwell.bedCar.exception.ResourceNotFoundException;
import com.maxwell.bedCar.model.SpaceModel;
import com.maxwell.bedCar.repository.SpaceRepository;
import com.maxwell.bedCar.service.SpaceService;
import com.maxwell.bedCar.util.SpaceMapper;

@Service
public class SpaceServiceImpl implements SpaceService {

	@Autowired
	private SpaceRepository repository;

	@Override
	public Boolean remove(Long id) {
		SpaceEntity entity = repository.findById(id).orElseThrow();
		if (Objects.isNull(entity)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}

	@Override
	public List<SpaceModel> findAll() {
		return SpaceMapper.entityToModelList(repository.findAll());
	}

	@Override
	public SpaceModel findById(Long id) {
		SpaceEntity entity = repository.findById(id).orElseThrow();
		return SpaceMapper.entityToModel(entity);
	}
	
	public SpaceEntity findEntityById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public SpaceModel add(SpaceEntity space) {
		try {
			return SpaceMapper.entityToModel(repository.save(space));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> add space -> " + e.getMessage());
		}
	}

	@Override
	public SpaceModel update(SpaceEntity space) {
		try {
			return SpaceMapper.entityToModel(repository.save(space));
		} catch (Exception e) {
			throw new ResourceNotFoundException("Something went wrong -> update space -> " + e.getMessage());
		}
	}

	@Override
	public List<SpaceModel> findByBusy(Boolean status) {
		return SpaceMapper.entityToModelList(repository.findByBusy(status));
	}
	
	public void updateSpace(SpaceEntity entity) {
		entity.setBusy(true);
		repository.save(entity);
	}
	
	public void updateSpaceById(Long id, Boolean status) {
		SpaceEntity entity = repository.findById(id).orElseThrow();
		entity.setBusy(status);
		repository.save(entity);
	}

}
