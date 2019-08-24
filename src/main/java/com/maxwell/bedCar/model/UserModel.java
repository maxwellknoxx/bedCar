package com.maxwell.bedCar.model;

import java.util.Set;

import com.maxwell.bedCar.entity.RoleEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class UserModel {

	private long id;
	private String name;
	private String username;
	private Set<RoleEntity> roles;

}