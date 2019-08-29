package com.maxwell.bedCar.controller;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.bedCar.entity.RoleEntity;
import com.maxwell.bedCar.entity.UserEntity;
import com.maxwell.bedCar.enums.RoleName;
import com.maxwell.bedCar.model.UserModel;
import com.maxwell.bedCar.repository.RoleRepository;
import com.maxwell.bedCar.repository.UserRepository;
import com.maxwell.bedCar.request.LoginForm;
import com.maxwell.bedCar.request.SignUpForm;
import com.maxwell.bedCar.response.JwtResponse;
import com.maxwell.bedCar.response.Response;
import com.maxwell.bedCar.security.jwt.JwtProvider;
import com.maxwell.bedCar.util.UserMapper;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthorizationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	/**
	 * 
	 * @param loginRequest
	 * @return
	 */
	@PostMapping("/api/v1/auth/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Response<UserModel> response = new Response<UserModel>();
		UserModel user;

		String jwt = "";

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		jwt = jwtProvider.generateJwtToken(authentication);

		user = UserMapper.entityToModel(userRepository.findByUsername(loginRequest.getUsername()).orElse(null));

		if (Objects.isNull(user)) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		response.setJwt(new JwtResponse(jwt));
		response.setData(user);
		response.setMessage(user.getRole());

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param signUpRequest
	 * @return
	 */
	@PostMapping("/api/v1/auth/signup")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<String>("Fail -> Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		UserEntity user = new UserEntity(signUpRequest.getName(), signUpRequest.getUsername(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<RoleEntity> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				RoleEntity adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

				break;
			default:
				RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		
		

		user = userRepository.save(user);

		return ResponseEntity.ok().body("Sucess");
	}

}
