package edu.eci.arsw.cinemApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinemApp.exceptions.PeliculaException;
import edu.eci.arsw.cinemApp.model.Cinema;
import edu.eci.arsw.cinemApp.model.Pelicula;
import edu.eci.arsw.cinemApp.persistence.CinemaPersistence;

@Service
public class CinemaServices {
	
	@Autowired
	private CinemaPersistence cinemaPersistence;
	
	public List<Cinema> getCinemas() throws PeliculaException{
		return cinemaPersistence.getCinemas();
	}

	
	public List<Cinema> getCinemasById(String id) throws PeliculaException{
		return cinemaPersistence.getCinemasById(id);
	}

}
