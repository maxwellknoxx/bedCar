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
		return VehicleModel.builder().id(entity.getId()).plate(entity.getPlate())
				.ownerName(entity.getOwnerName()).ownerDocumentNumber(entity.getOwnerDocumentNumber())
				.ownerAddress(entity.getOwnerAddress()).plan(entity.getPlan()).subscriptionDate(entity.getSubscriptionDate())
				.dueDate("Every day " + entity.getDueDate())
				.spaceId(entity.getSpace().getId()).build();
	}

	public static List<VehicleModel> entitiesToModelList(List<VehicleEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> VehicleModel.builder().id(entity.getId()).plate(entity.getPlate())
						.ownerName(entity.getOwnerName()).ownerDocumentNumber(entity.getOwnerDocumentNumber())
						.ownerAddress(entity.getOwnerAddress()).plan(entity.getPlan())
						.subscriptionDate(entity.getSubscriptionDate())
						.dueDate("Every day " + entity.getDueDate())
						.spaceId(entity.getSpace().getId()).build())
				.collect(Collectors.toList());
	}
	
	public static Integer getPlanDays(String plan) {
		if(plan.contains("BiWeekly")) {
			return 15;
		}
		return 30;
	}
	
	public static String getPlanString(Integer days) {
		if(days == 15) {
			return "BiWeekly - 80";
		}
		return "Monthly - 140";
	}

}
