package com.maxwell.bedCar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "owners")
@Getter
@Setter
@ToString
public class OwnerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "document_number", nullable = false)
	private String documentNumber;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "owner_has_vehicles", joinColumns = @JoinColumn(name = "owner_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
	private List<VehicleEntity> vehicles = new ArrayList<>();


}
