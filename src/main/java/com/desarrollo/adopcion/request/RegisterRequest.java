package com.desarrollo.adopcion.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
	
	@NotBlank
	String nombre;
	@NotBlank
	String apellido;
	@NotBlank
	String correo;
	@NotBlank
	String clave;

}
