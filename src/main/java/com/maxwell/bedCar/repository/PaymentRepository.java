package com.maxwell.bedCar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.bedCar.entity.PaymentEntity;
import com.maxwell.bedCar.entity.VehicleEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>{

	List<PaymentEntity> findAll();
	
	List<PaymentEntity> findByPaymentDate(String date);
	
	List<PaymentEntity> findByVehicle(VehicleEntity vehicle);
	
	List<PaymentEntity> findByVehiclePlate(String plate);
	
	Long countByPaidValue(String paidValue);

}
