package edu.eci.arsw.cinemApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinemApp.exceptions.UsuarioException;
import edu.eci.arsw.cinemApp.model.Usuario;
import edu.eci.arsw.cinemApp.persistence.UsuarioPersistence;

@Service
public class UsuarioServices {
	
	@Autowired
	private UsuarioPersistence usuarioPersistence;

	public List<Usuario> getAllUsers() throws UsuarioException{
		return usuarioPersistence.getAllUsers();
	}

	public void createNewUser(Usuario user) throws UsuarioException {
		usuarioPersistence.createNewUser(user);
	}

	public Usuario getUsers(String username) throws UsuarioException {
		return usuarioPersistence.getUsuarioByUsername(username);
	}
	
	
}
