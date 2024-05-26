package com.desarrollo.adopcion.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.desarrollo.adopcion.exception.UserException;
import com.desarrollo.adopcion.modelo.Estado;
import com.desarrollo.adopcion.modelo.Role;
import com.desarrollo.adopcion.modelo.User;
import com.desarrollo.adopcion.repository.IUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	
	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		if(userRepository.existsByCorreo(user.getCorreo())) {
			throw new UserException(user.getCorreo()+" ya está registrado!");
		}
		user.setClave(passwordEncoder.encode(user.getClave()));
		user.setEstado(Estado.ACTIVO);
		user.setRole(Role.USER);
		return userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Transactional
	@Override
	public void deleteUser(String correo) {
		User user = getUserByCorreo(correo);
		if(user!=null) {
			userRepository.deleteByCorreo(correo);
		}
		
	}

	@Override
	public User getUserByCorreo(String correo) {
		System.out.println("Correo "+correo);
		return userRepository.findByCorreo(correo).orElseThrow(()->new UserException("Usuario No encontrado..."));
	}


}
