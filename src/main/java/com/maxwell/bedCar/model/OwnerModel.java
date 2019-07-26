package com.maxwell.bedCar.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class OwnerModel {

	private Long id;
	private String name;
	private String documentNumber;
	private String address;
	private List<VehicleModel> vehicles;
	
}
