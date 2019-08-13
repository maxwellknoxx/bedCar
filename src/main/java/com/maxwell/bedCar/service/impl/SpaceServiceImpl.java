package com.maxwell.bedCar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.bedCar.entity.SpaceEntity;
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
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<SpaceModel> findAll() {
		List<SpaceEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return SpaceMapper.entityToModelList(list);
	}

	@Override
	public SpaceModel findById(Long id) {
		SpaceEntity entity = repository.findById(id).orElseThrow();
		if (entity == null) {
			return null;
		}
		return SpaceMapper.entityToModel(entity);
	}

	public SpaceEntity findEntityById(Long id) {
		SpaceEntity entity = repository.findById(id).orElseThrow();
		if (entity == null) {
			return null;
		}
		return entity;
	}

	@Override
	public SpaceModel add(SpaceEntity space) {
		SpaceEntity entity = repository.save(space);
		if (entity == null) {
			return null;
		}
		return SpaceMapper.entityToModel(entity);
	}

	@Override
	public SpaceModel update(SpaceEntity space) {
		SpaceEntity entity = repository.save(space);
		if (entity == null) {
			return null;
		}
		return SpaceMapper.entityToModel(entity);
	}

	@Override
	public List<SpaceModel> findByBusy(Boolean status) {
		List<SpaceEntity> list = repository.findByBusy(status);
		if (list.isEmpty()) {
			return null;
		}
		return SpaceMapper.entityToModelList(list);
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
