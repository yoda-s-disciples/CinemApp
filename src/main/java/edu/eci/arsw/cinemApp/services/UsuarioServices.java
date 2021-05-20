package edu.eci.arsw.cinemApp.services;

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

	public Object getUsers(Object email) throws UsuarioException {
		return usuarioPersistence.getUsers((String) email);
	}

	public Object getUser() throws UsuarioException{
		return usuarioPersistence.getUser();
	}

	public Object getUserByCorreo(String correo) throws UsuarioException{
		return usuarioPersistence.getUserByCorreo(correo);
	}
	
	
}
