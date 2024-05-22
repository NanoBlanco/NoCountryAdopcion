package com.desarrollo.adopcion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desarrollo.adopcion.modelo.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	boolean existsByRole(Role role);
	
	Optional<Role> findByRole(String userRole);
}
