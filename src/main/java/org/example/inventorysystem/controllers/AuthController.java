package org.example.inventorysystem.controllers;

import org.example.inventorysystem.models.JwtResponse;
import org.example.inventorysystem.models.LoginRequest;
import org.example.inventorysystem.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtUtil.generateToken(authentication);
		return ResponseEntity.ok(new JwtResponse(jwtToken));
	}
}
