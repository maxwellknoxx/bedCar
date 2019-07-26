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

	private long id;
	private String carPlate;


}