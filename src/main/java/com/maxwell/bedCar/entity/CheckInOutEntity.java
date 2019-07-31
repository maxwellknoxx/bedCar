package com.maxwell.bedCar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name = "Check_in_out")
public class CheckInOutEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "check_in_hour", nullable = false)
	private String checkInHour;
	
	@Column(name = "Check_out_hour", nullable = true)
	private String checkOutHour;
	
	@Column(name = "car_plate", nullable = false)
	private String carPlate;
	
	@Column(name = "space_id", nullable = false)
	private Long spaceId;


}
