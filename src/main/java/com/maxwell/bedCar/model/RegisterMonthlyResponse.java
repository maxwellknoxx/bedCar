package com.maxwell.bedCar.model;

import java.util.List;

import com.maxwell.bedCar.entity.SpaceEntity;
import com.maxwell.bedCar.entity.VehicleEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class RegisterMonthlyResponse {

	private List<VehicleEntity> vehicles;
	private List<SpaceEntity> spaces;

}
