package edu.eci.arsw.cinemApp.persistence;

import java.util.List;

import edu.eci.arsw.cinemApp.exceptions.UsuarioException;
import edu.eci.arsw.cinemApp.model.Usuario;

public interface UsuarioPersistence {

	public void createNewUser(Usuario user) throws UsuarioException;

	public Usuario getUsers(String email) throws UsuarioException;

}
