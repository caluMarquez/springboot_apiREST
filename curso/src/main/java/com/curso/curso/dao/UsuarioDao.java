package com.curso.curso.dao;

import java.util.List;

import com.curso.curso.models.Usuario;

public interface UsuarioDao {
	
	List<Usuario> getUsuarios();

	void EliminarUsuario(Long id);

	void registrar(Usuario usuario);

	Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

}
