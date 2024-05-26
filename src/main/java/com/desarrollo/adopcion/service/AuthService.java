package com.desarrollo.adopcion.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desarrollo.adopcion.modelo.Role;
import com.desarrollo.adopcion.modelo.User;
import com.desarrollo.adopcion.repository.IUserRepository;
import com.desarrollo.adopcion.request.LoginRequest;
import com.desarrollo.adopcion.request.RegisterRequest;
import com.desarrollo.adopcion.response.AuthResponse;
import com.desarrollo.adopcion.security.jwt.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final IUserRepository userRepository;
	private final JwtUtils jwtUtils;
	
	public AuthResponse login(LoginRequest request) {
		User user = userRepository.findByCorreo(request.getCorreo()).orElseThrow(()-> new UsernameNotFoundException("Usuario No encontrado..."));
		return null;
	}

	/*
	public AuthResponse register(RegisterRequest request) {
		User user = User.builder()
				.nombre(request.getNombre())
				.apellido(request.getApellido())
				.correo(request.getCorreo())
				.clave(request.getClave())
				.role(Role.USER)
				.build();
		userRepository.save(user);
		return AuthResponse.builder().token(jwtUtils.getToken(user)).build();
	}
	*/
}
