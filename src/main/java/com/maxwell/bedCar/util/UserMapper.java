package com.maxwell.bedCar.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.bedCar.entity.UserEntity;
import com.maxwell.bedCar.model.UserModel;

@Component
public class UserMapper {

	public static UserModel entityToModel(UserEntity entity) {
		return UserModel.builder().id(entity.getId()).username(entity.getUsername())
				.name(entity.getName())
				.role(entity.getRole().getName().toString()).build();

	}

	public static List<UserModel> entitiesToModels(List<UserEntity> entities) {
		return entities
				.stream().filter(Objects::nonNull).map(entity -> UserModel.builder().id(entity.getId())
						.username(entity.getUsername())
						.name(entity.getName())
						.role(entity.getRole().getName().toString()).build())
				.collect(Collectors.toList());
	}

}
