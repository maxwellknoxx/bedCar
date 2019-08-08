package com.maxwell.bedCar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.bedCar.entity.SpaceEntity;

@Repository
public interface SpaceRepository extends JpaRepository<SpaceEntity, Long> {

	List<SpaceEntity> findAll();

	Optional<SpaceEntity> findById(Long id);
	
	List<SpaceEntity> findByBusy(Boolean status);
	
	

	
}
