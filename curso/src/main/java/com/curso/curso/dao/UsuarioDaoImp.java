package com.curso.curso.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.curso.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Usuario> getUsuarios() {
		
		//Esta consulta es parecida a SQL pero es consulta a Hibernate
		String query = "FROM Usuario";
		List<Usuario> resultado = entityManager.createQuery(query).getResultList();
		
		return resultado;
	}

	@Override
	public void EliminarUsuario(Long id) {
	
		Usuario usuario = entityManager.find(Usuario.class, id);
		entityManager.remove(usuario);
	}

	@Override
	public void registrar(Usuario usuario) {
		entityManager.merge(usuario);
		
	}

	@Override
	public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
		
	String query = "FROM Usuario WHERE email = :email ";
	
	
	List<Usuario> lista = entityManager.createQuery(query)
	.setParameter("email", usuario.getEmail())
	.getResultList();
		
	
	
	if(lista.isEmpty()) {
		return null;
	}
	
	String passwordHashed = lista.get(0).getPassword();
	
	Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
	
if(argon2.verify(passwordHashed, usuario.getPassword())) {
		
		
		return lista.get(0);
	};
	

	return null;
	
	}

}
