package com.maxwell.bedCar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.bedCar.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findAll();

	Optional<UserEntity> findById(Long id);
	
	Optional<UserEntity> findByUsername(String username);
	
	Boolean existsByUsername(String username);

}