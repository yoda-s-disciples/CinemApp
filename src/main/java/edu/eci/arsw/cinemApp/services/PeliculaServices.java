package edu.eci.arsw.cinemApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinemApp.exceptions.PeliculaException;
import edu.eci.arsw.cinemApp.model.Pelicula;
import edu.eci.arsw.cinemApp.persistence.PeliculaPersistence;

@Service
public class PeliculaServices {
	
	@Autowired
	private PeliculaPersistence peliculaPersistence;

	public List<Pelicula> getPeliculas() throws PeliculaException{
		System.out.println("Services");
		return peliculaPersistence.getPeliculas();
	}

}
