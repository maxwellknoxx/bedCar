package com.maxwell.bedCar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.bedCar.entity.OwnerEntity;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {

	List<OwnerEntity> findAll();

	Optional<OwnerEntity> findById(Long id);

}