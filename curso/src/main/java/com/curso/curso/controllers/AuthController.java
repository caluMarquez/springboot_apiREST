package com.curso.curso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.curso.dao.UsuarioDao;
import com.curso.curso.models.Usuario;
import com.curso.curso.utils.JWTUtil;

@RestController
public class AuthController {

	@Autowired
	private UsuarioDao usuariodao; 
	
	@Autowired
	private JWTUtil jwtUtil;
	
	//Inicio de sesion
	@RequestMapping(value= "api/login", method = RequestMethod.POST)
	public String login(@RequestBody Usuario usuario) {
		
		
		
		Usuario usuarioLogeado = usuariodao.obtenerUsuarioPorCredenciales(usuario);
			
		System.out.println("Usuario logeado que llego del dao: "+usuarioLogeado);
			if(usuarioLogeado != null) {
				
				String tokenJWT = jwtUtil.create(String.valueOf(usuarioLogeado.getId()),usuarioLogeado.getEmail());
			
				return tokenJWT;
				
			}
			

	
		
		return "MAL";
	}
	
	
}
