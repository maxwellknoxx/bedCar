package com.maxwell.bedCar.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.bedCar.entity.SpaceEntity;
import com.maxwell.bedCar.model.SpaceModel;

@Component
public class SpaceMapper {

	public static SpaceModel entityToModel(SpaceEntity entity) {
		return SpaceModel.builder().id(entity.getId()).busy(entity.getBusy()).build();
	}

	public static SpaceEntity modelToEntity(SpaceModel model) {
		return SpaceEntity.builder().id(model.getId()).busy(model.getBusy()).build();
	}

	public static List<SpaceModel> entityToModelList(List<SpaceEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> SpaceModel.builder().id(entity.getId()).busy(entity.getBusy()).build())
				.collect(Collectors.toList());
	}

	public static List<SpaceEntity> modelToEntityList(List<SpaceModel> models) {
		return models.stream().filter(Objects::nonNull)
				.map(entity -> SpaceEntity.builder().id(entity.getId()).busy(entity.getBusy()).build())
				.collect(Collectors.toList());
	}

}
