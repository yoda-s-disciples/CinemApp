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

	public void createNewUser(Usuario user) throws UsuarioException {
		usuarioPersistence.createNewUser(user);
	}

	public Object getUsers(String email) throws UsuarioException {
		System.out.println("Services");
		return usuarioPersistence.getUsers((String) email);
	}
	
	
}
