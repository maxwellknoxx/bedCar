package com.maxwell.bedCar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.bedCar.entity.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>{

	List<PaymentEntity> findAll();
	
	PaymentEntity findByOwnerId(Long id);
	
	List<PaymentEntity> findByPaymentDate(String date);
	
}
