package com.maxwell.bedCar.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.bedCar.entity.VehicleEntity;
import com.maxwell.bedCar.model.VehicleModel;

@Component
public class VehicleMapper {

	public static VehicleModel entityToModel(VehicleEntity entity) {
		return VehicleModel.builder().id(entity.getId()).carPlate(entity.getCarPlate()).build();
	}

	public static List<VehicleModel> entitiesToModels(List<VehicleEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> VehicleModel.builder().id(entity.getId()).carPlate(entity.getCarPlate()).build())
				.collect(Collectors.toList());
	}

}
