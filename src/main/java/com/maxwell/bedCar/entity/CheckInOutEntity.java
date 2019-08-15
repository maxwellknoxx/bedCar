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
	
	@Column(name = "checkin_data", nullable = false)
	private String checkInDate;
	
	@Column(name = "check_in_hour", nullable = false)
	private String checkInHour;
	
	@Column(name = "Check_out_hour", nullable = true)
	private String checkOutHour;
	
	@Column(name = "total_hours", nullable = true)
	private String totalHours;
	
	@Column(name = "plate", nullable = false)
	private String plate;
	
	@Column(name = "value", nullable = true)
	private String value;
	
	@Column(name = "space_id", nullable = false)
	private Long spaceId;
	
	@Column(name = "status", nullable = false)
	private Boolean status;
	
}
