package com.maxwell.bedCar.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

	private T data;
	private String message;
	private JwtResponse jwt;

}
