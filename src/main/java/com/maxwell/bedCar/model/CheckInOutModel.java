package com.maxwell.bedCar.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class CheckInOutModel {

	private Long id;
	private String checkInHour;
	private String checkOutHour;
	private String carPlate;
	private Long spaceId;
	
}
