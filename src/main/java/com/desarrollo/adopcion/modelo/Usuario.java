package com.desarrollo.adopcion.modelo;

import java.util.Collection;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private String correo;
	private String clave;
	private boolean estado;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="usuario_roles", 
	joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id"))
	private Collection<Rol> roles = new HashSet<>();

}