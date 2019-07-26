package com.maxwell.bedCar.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.bedCar.entity.OwnerEntity;
import com.maxwell.bedCar.model.OwnerModel;

@Component
public class OwnerMapper {

	public static OwnerModel entityToModel(OwnerEntity entity) {
		return OwnerModel.builder().id(entity.getId()).name(entity.getName()).documentNumber(entity.getDocumentNumber())
				.address(entity.getAddress()).vehicles(VehicleMapper.entitiesToModels(entity.getVehicles())).build();
	}

	public static List<OwnerModel> entitiesToModels(List<OwnerEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> OwnerModel.builder().id(entity.getId()).name(entity.getName())
						.documentNumber(entity.getDocumentNumber()).address(entity.getAddress())
						.vehicles(VehicleMapper.entitiesToModels(entity.getVehicles())).build())
				.collect(Collectors.toList());
	}

}
