package com.maxwell.bedCar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxwell.bedCar.entity.CheckInOutEntity;

public interface CheckInOutRepository extends JpaRepository<CheckInOutEntity, Long> {

	List<CheckInOutEntity> findAll();

	Optional<CheckInOutEntity> findById(Long id);

	CheckInOutEntity findByPlate(String Plate);

}
