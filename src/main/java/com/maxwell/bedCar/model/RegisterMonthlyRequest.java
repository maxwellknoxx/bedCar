package com.maxwell.bedCar.model;

import java.util.List;

import com.maxwell.bedCar.entity.VehicleEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class RegisterMonthlyRequest {

	private Long ownerId;
	private String ownerName;
	private String ownerDocumentNumber;
	private String ownerAddress;
	private List<VehicleEntity> vehicles;

}
