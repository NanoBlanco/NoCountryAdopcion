package com.desarrollo.adopcion.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.desarrollo.adopcion.modelo.User;
import com.desarrollo.adopcion.repository.IUserRepository;

@Service
public class UserDetailsSecService implements UserDetailsService {
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByCorreo(username).orElseThrow(()-> new UsernameNotFoundException("Usuario No encontrado..."));
		return UserDetailsSec.buildUserDetails(user);
	}

}
