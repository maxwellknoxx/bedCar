package com.maxwell.bedCar.model;

import com.maxwell.bedCar.enums.RoleName;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class RoleModel {

	private Long id;
	private RoleName name;

}
