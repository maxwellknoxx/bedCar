package com.maxwell.bedCar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.bedCar.entity.VehicleEntity;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long>{

	List<VehicleEntity> findAll();
	
	List<VehicleEntity> findByPlan(String plan);
	
	Long countByPlan(String plan);
	
	VehicleEntity findByPlate(String plate);
	
	Optional<VehicleEntity> findById(Long id);
	
	
	
}
