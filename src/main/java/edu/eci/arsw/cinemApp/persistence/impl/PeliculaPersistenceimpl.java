package edu.eci.arsw.cinemApp.persistence.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinemApp.DB.CinemAppDB;
import edu.eci.arsw.cinemApp.exceptions.PeliculaException;
import edu.eci.arsw.cinemApp.model.Pelicula;
import edu.eci.arsw.cinemApp.persistence.PeliculaPersistence;

@Service
public class PeliculaPersistenceimpl implements PeliculaPersistence{
	
	CinemAppDB cineMapDB = new CinemAppDB();
	
	@Override
	public List<Pelicula> getPeliculas() throws PeliculaException {
		return cineMapDB.getPeliculas();
	}

	@Override
	public List<Pelicula> getPeliculaByID(String id) throws PeliculaException {
		return cineMapDB.getPeliculaById(id);
	}

}
