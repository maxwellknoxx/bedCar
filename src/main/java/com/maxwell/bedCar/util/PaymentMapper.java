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
		return PaymentModel.builder().id(entity.getId()).ownerId(entity.getOwner().getId())
				.ownerName(entity.getOwner().getName()).paymentDate(entity.getPaymentDate())
				.paidValue(entity.getPaidValue()).build();
	}

	public static List<PaymentModel> entitiesToModelList(List<PaymentEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> PaymentModel.builder().id(entity.getId()).ownerId(entity.getOwner().getId())
						.ownerName(entity.getOwner().getName()).paymentDate(entity.getPaymentDate())
						.paidValue(entity.getPaidValue()).build())
				.collect(Collectors.toList());
	}

}
