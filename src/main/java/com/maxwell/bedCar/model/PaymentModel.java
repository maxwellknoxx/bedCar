package com.maxwell.bedCar.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PaymentModel {

	private Long id;
	private String ownerName;
	private String plate;
	private String paymentDate;
	private String plan;
	private String paidValue;

}
