package edu.eci.arsw.cinemApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinemApp.exceptions.PeliculaException;
import edu.eci.arsw.cinemApp.model.Cinema;
import edu.eci.arsw.cinemApp.persistence.CinemaPersistence;

@Service
public class CinemaServices {
	
	@Autowired
	private CinemaPersistence cinemaPersistence;

	public List<Cinema> getCinemas() throws PeliculaException{
		System.out.println("services");
		return cinemaPersistence.getCinemas();
	}

}
