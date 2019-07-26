package com.maxwell.bedCar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.bedCar.entity.RoleEntity;
import com.maxwell.bedCar.enums.RoleName;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
	Optional<RoleEntity> findByName(RoleName roleName);

}
