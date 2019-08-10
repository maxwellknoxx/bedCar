package com.maxwell.bedCar.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.bedCar.entity.PaymentEntity;
import com.maxwell.bedCar.model.PaymentModel;

@Component
public class PaymentMapper {

	public static PaymentModel entityToModel(PaymentEntity entity) {
		return PaymentModel.builder().id(entity.getId())
				.ownerName(entity.getVehicle().getOwnerName())
				.vehicleId(entity.getVehicle().getId())
				.plate(entity.getVehicle().getPlate())
				.paymentDate(entity.getPaymentDate())
				.paidValue(entity.getPaidValue())
				.plan(entity.getVehicle().getPlan())
				.build();
	}

	public static List<PaymentModel> entitiesToModelList(List<PaymentEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> PaymentModel.builder().id(entity.getId())
						.ownerName(entity.getVehicle().getOwnerName())
						.vehicleId(entity.getVehicle().getId())
						.plate(entity.getVehicle().getPlate())
						.paymentDate(entity.getPaymentDate())
						.paidValue(entity.getPaidValue())
						.plan(entity.getVehicle().getPlan())
						.build())
				.collect(Collectors.toList());
	}

}
