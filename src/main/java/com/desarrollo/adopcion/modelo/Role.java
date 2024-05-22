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
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String rol;
	
	@ManyToMany(mappedBy="roles")
	private Collection<User> users = new HashSet<>();
	
	public void assignRolToUser(User usr) {
		usr.getRoles().add(this);
		this.getUsers().add(usr);
	}
	
	public void removeUserFromRol(User usr) {
		usr.getRoles().remove(this);
		this.getUsers().remove(usr);
	}
	
	public void removeAllUsersFromRol() {
		if(this.getUsers()!=null) {
			List<User> rolUsuarios = this.getUsers().stream().toList();
			rolUsuarios.forEach(this :: removeUserFromRol);
		}
	}

	public Role(String rolName) {
		this.rol = rolName;
	}

}
