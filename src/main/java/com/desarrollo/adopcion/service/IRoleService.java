package com.desarrollo.adopcion.service;

import java.util.List;

import com.desarrollo.adopcion.modelo.Role;
import com.desarrollo.adopcion.modelo.User;


public interface IRoleService {
	
	List<Role> getRoles();
	
	Role createRole(Role role);
	
	void deleteRole(Long roleId);
	
	Role findByName(String rol);
	
	User removeUserFromRol(Long userId, Long roleId);
	
	User assignRolToUser(Long userId, Long roleId);
	
	Role removeAllUsersFromRol(Long role);

}
