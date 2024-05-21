package com.desarrollo.adopcion.modelo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String rol;
	
	@ManyToMany(mappedBy="roles")
	private Collection<Usuario> usuarios = new HashSet<>();
	
	public void assignRolToUser(Usuario usr) {
		usr.getRoles().add(this);
		this.getUsuarios().add(usr);
	}
	
	public void removeUserFromRol(Usuario usr) {
		usr.getRoles().remove(this);
		this.getUsuarios().remove(usr);
	}
	
	public void removeAllUsersFromRol() {
		if(this.getUsuarios()!=null) {
			List<Usuario> rolUsuarios = this.getUsuarios().stream().toList();
			rolUsuarios.forEach(this :: removeUserFromRol);
		}
	}

	public Rol(String rolName) {
		this.rol = rolName;
	}

}
