package com.maxwell.bedCar.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.bedCar.entity.CheckInOutEntity;
import com.maxwell.bedCar.model.CheckInOutModel;

@Component
public class CheckInOutMapper {

	public static CheckInOutModel entityToModel(CheckInOutEntity entity) {
		return CheckInOutModel.builder().id(entity.getId()).checkInHour(entity.getCheckInHour())
				.checkOutHour(entity.getCheckOutHour()).plate(entity.getPlate()).checkInDate(entity.getCheckInDate())
				.totalHours(entity.getTotalHours())
				.status(entity.getStatus())
				.value(entity.getValue()).spaceId(entity.getSpaceId()).build();
	}

	public static List<CheckInOutModel> entitiesToModelList(List<CheckInOutEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> CheckInOutModel.builder().id(entity.getId()).checkInHour(entity.getCheckInHour())
						.checkOutHour(entity.getCheckOutHour()).plate(entity.getPlate())
						.totalHours(entity.getTotalHours())
						.checkInDate(entity.getCheckInDate()).value(entity.getValue()).spaceId(entity.getSpaceId())
						.status(entity.getStatus())
						.build())
				.collect(Collectors.toList());
	}

}
