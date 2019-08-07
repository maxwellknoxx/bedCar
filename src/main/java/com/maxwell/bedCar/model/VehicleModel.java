package com.maxwell.bedCar.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class VehicleModel {
	
	private Long id;
	private String plate;
	private String ownerName;
	private String ownerDocumentNumber;
	private String ownerAddress;
	private String plan;
	private String subscriptionDate;
	private String dueDate;
	private SpaceModel space;

}
