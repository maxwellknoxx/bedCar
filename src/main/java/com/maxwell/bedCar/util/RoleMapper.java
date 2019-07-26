package com.maxwell.bedCar.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.maxwell.bedCar.entity.RoleEntity;
import com.maxwell.bedCar.model.RoleModel;

public class RoleMapper {

	public static RoleModel entityToModel(RoleEntity entity) {
		return RoleModel.builder().id(entity.getId()).name(entity.getName()).build();
	}

	public static List<RoleModel> entitiesToModels(List<RoleEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> RoleModel.builder().id(entity.getId()).name(entity.getName()).build())
				.collect(Collectors.toList());
	}

}
