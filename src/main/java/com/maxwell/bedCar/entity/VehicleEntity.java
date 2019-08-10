package com.maxwell.bedCar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "vehicles", uniqueConstraints = { @UniqueConstraint(columnNames = { "plate" }) })
public class VehicleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId
	@Column(name = "plate", nullable = false)
	private String plate;
	
	@Column(name = "owner_name", nullable = false)
	private String ownerName;

	@Column(name = "owner_document_number", nullable = false)
	private String ownerDocumentNumber;

	@Column(name = "owner_address", nullable = false)
	private String ownerAddress;

	@Column(name = "plan", nullable = false)
	private String plan;

	@Column(name = "subscription_date", nullable = false)
	private String subscriptionDate;
	
	@Column(name = "due_date", nullable = false)
	private String dueDate;
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "space_id_fk", nullable = true)
	private SpaceEntity space;
	
	public VehicleEntity(String plate) {
		this.plate = plate;
	}
	
	public VehicleEntity() {
	}

}
