package com.desarrollo.adopcion;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desarrollo.adopcion.controller.AuthController;
import com.desarrollo.adopcion.service.UserService;
import com.desarrollo.adopcion.modelo.User;
import com.desarrollo.adopcion.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class AuthControllerTest {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private AuthController authController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(authController);
		mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
	}
	
	@Test
	public void testRegistroUsuario() throws Exception {
		User usuario = new User();
		usuario.setNombre("John");
		usuario.setApellido("Doe");
		usuario.setCorreo("johndoe@example.com");
		usuario.setClave("password");
		
		when(userService.saveUser(any(User.class))).thenReturn(usuario);
		
		mockMvc.perform(post("/auth/registro")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(usuario)))
		.andExpect(status().isOk());
	}

	@Test
	public void testLoginUsuario() throws Exception {
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setCorreo("johndoe@example.com");
		loginRequest.setClave("password");
		
		User usuario = new User();
		usuario.setCorreo("johndoe@example.com");
		usuario.setClave("password");
		
		mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(loginRequest)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.correo",is("johndoe@example.com")));
	}
}
