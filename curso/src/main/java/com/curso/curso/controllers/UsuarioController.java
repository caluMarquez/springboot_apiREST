package com.curso.curso.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.curso.dao.UsuarioDao;
import com.curso.curso.models.Usuario;
import com.curso.curso.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {
 
	@Autowired
	private UsuarioDao usuariodao;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	//Eliminar Usuario
	@RequestMapping(value= "api/usuarios/{id}", method = RequestMethod.DELETE)
	public void eliminarUsuario(@PathVariable Long id) {
		
		usuariodao.EliminarUsuario(id);
	}
	
	
	//Obtener listado de usuarios
	@RequestMapping(value= "api/usuarios")
	public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
		
		String usuarioId = jwtUtil.getKey(token);
		System.out.println("UsuarioId del controller "+usuarioId);
		if(usuarioId == null) {
			return new ArrayList<>();
		}
		
		return usuariodao.getUsuarios();
		
	}
	
	
	//Crear un nuevo usuario
	@RequestMapping(value= "api/usuarios", method = RequestMethod.POST)
	public void registrarUsuario(@RequestBody Usuario usuario) {
		
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash = argon2.hash(1, 1024, 1,usuario.getPassword());
		usuario.setPassword(hash);
		
		
		usuariodao.registrar(usuario);
		
	}
	
	

}
