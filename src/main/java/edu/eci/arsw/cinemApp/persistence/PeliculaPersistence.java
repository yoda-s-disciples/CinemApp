package edu.eci.arsw.cinemApp.persistence;

import java.util.List;

import edu.eci.arsw.cinemApp.exceptions.PeliculaException;
import edu.eci.arsw.cinemApp.model.Pelicula;

public interface PeliculaPersistence {

	public List<Pelicula> getPeliculas() throws PeliculaException;

	public List<Pelicula> getPeliculaByID(String id) throws PeliculaException;

}
