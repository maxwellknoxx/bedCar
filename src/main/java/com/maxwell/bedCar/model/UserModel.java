package com.maxwell.bedCar.model;

import java.util.List;

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
	private String username;
	private String password;
	private List<RoleModel> roles;

}