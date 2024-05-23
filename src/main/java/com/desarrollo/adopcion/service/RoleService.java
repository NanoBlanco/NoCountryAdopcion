package com.desarrollo.adopcion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desarrollo.adopcion.modelo.Role;
import com.desarrollo.adopcion.modelo.User;
import com.desarrollo.adopcion.repository.IRoleRepository;
import com.desarrollo.adopcion.repository.IUserRepository;
import com.desarrollo.adopcion.exception.RoleException;
import com.desarrollo.adopcion.exception.UserException;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private IRoleRepository roleRepository;
	@Autowired
	private IUserRepository userRepository;

	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Role createRole(Role role) {
		String roleName = "ROLE_"+role.getRol().toUpperCase();
		Role rol = new Role(roleName);
		if(roleRepository.existsByRole(role)) {
			throw new RoleException(rol.getRol()+" Ya existe!");
		}
		return roleRepository.save(rol);
	}

	@Override
	public void deleteRole(Long id) {
		this.removeAllUsersFromRol(id);
		roleRepository.deleteById(id);
		
	}

	@Override
	public Role findByName(String rol) {
		return roleRepository.findByRole(rol).get();
	}

	@Override
	public User removeUserFromRol(Long userId, Long roleId) {
		Optional<User> user = userRepository.findById(userId);
		Optional<Role> role = roleRepository.findById(roleId);
		if(role.isPresent() && role.get().getUsers().contains(user.get())) {
			role.get().removeUserFromRol(user.get());
			roleRepository.save(role.get());
			return user.get();
		}
		throw new UserException("Usuario No encontrado");
	}

	@Override
	public User assignRolToUser(Long userId, Long roleId) {
		Optional<User> user = userRepository.findById(userId);
		Optional<Role> role = roleRepository.findById(roleId);
		if(user.isPresent() && user.get().getRoles().contains(role.get())) {
			throw new UserException(user.get().getNombre()+"ya tiene asignado el rol: "+role.get().getRol());
		}
		if(role.isPresent()) {
			role.get().assignRolToUser(user.get());
			roleRepository.save(role.get());
		}
		return user.get();
	}

	@Override
	public Role removeAllUsersFromRol(Long id) {
		Optional<Role> role = roleRepository.findById(id);
		role.get().removeAllUsersFromRol();
		return roleRepository.save(role.get());
	}

}
