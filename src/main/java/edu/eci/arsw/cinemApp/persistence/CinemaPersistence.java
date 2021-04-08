package edu.eci.arsw.cinemApp.persistence;

import java.util.List;

import edu.eci.arsw.cinemApp.model.Cinema;
import edu.eci.arsw.cinemApp.model.Pelicula;

public interface CinemaPersistence {
	
	public List<Cinema> getCinemas();
	public List<Cinema> getCinemasById(String id);

}
