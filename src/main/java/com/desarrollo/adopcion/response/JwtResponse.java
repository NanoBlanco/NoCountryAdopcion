package com.desarrollo.adopcion.response;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {

	private Long id;
    private String correo;
    private String token;
    private String type = "Bearer";
    private List<String> roles;

    public JwtResponse(Long id, String email, String token, List<String> roles) {
        this.id = id;
        this.correo = email;
        this.token = token;
        this.roles = roles;
    }
	
}
