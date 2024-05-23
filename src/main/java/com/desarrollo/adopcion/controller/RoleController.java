package com.desarrollo.adopcion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desarrollo.adopcion.service.IRoleService;
import com.desarrollo.adopcion.exception.RoleException;
import com.desarrollo.adopcion.modelo.Role;
import com.desarrollo.adopcion.modelo.User;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Role>> getAllRoles(){
		return new ResponseEntity<>(roleService.getRoles(), HttpStatus.FOUND);
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createRole(@RequestBody Role role){
		try {
			roleService.createRole(role);
			return ResponseEntity.ok("Nuevo Rol creado con exito");
		}catch(RoleException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{roleId}")
	public void deleteRole(@PathVariable Long roleId) {
		roleService.deleteRole(roleId);
	}
	
	@PostMapping("/remove-all/{roleId}")
	public Role removeAllUsersFromRole(@PathVariable Long roleId) {
		return roleService.removeAllUsersFromRol(roleId);
	}
	
	@PostMapping("/remove-user")
	public User removeUserFromRole(@RequestParam("roleId") Long roleId, @RequestParam("userId") Long userId) {
		return roleService.removeUserFromRol(userId, roleId);
	}

	@PostMapping("/assign-role")
	public User assignToleToUser(@RequestParam("roleId") Long roleId, @RequestParam("userId") Long userId) {
		return roleService.assignRolToUser(userId, roleId);	
	}

}
