package edu.eci.arsw.cinemApp.persistence.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import edu.eci.arsw.cinemApp.DB.CinemAppDB;
import edu.eci.arsw.cinemApp.exceptions.UsuarioException;
import edu.eci.arsw.cinemApp.model.Usuario;
import edu.eci.arsw.cinemApp.persistence.UsuarioPersistence;

@Service
public class UsuarioPersistenceimpl implements UsuarioPersistence{
	
	CinemAppDB cineMapDB = new CinemAppDB();

	@Override
	public Usuario getUsers(String email) throws UsuarioException{
		return cineMapDB.getUsers(email);
	}

	@Override
	public void createNewUser(Usuario user) throws UsuarioException {
			cineMapDB.createNewUser(user);
	}

	@Override
	public Object getUser() throws UsuarioException {
		return cineMapDB.getUser();
	}

	@Override
	public Object getUserByCorreo(String correo) throws UsuarioException {
		return cineMapDB.getUserByCorreo(correo);
	}

}
