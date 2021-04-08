package edu.eci.arsw.cinemApp.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.cinemApp.DB.CinemAppDB;
import edu.eci.arsw.cinemApp.model.Cinema;
import edu.eci.arsw.cinemApp.model.Pelicula;
import edu.eci.arsw.cinemApp.persistence.CinemaPersistence;

@Service
public class CinemaPersistencimpl implements CinemaPersistence{
	
	CinemAppDB cineMapDB = new CinemAppDB();
	
	@Override
	public List<Cinema> getCinemas() {
		return cineMapDB.getCinemas();
	}
	
	@Override
	public List<Cinema> getCinemasById(String id) {
		System.out.println("Persistencia");
		return cineMapDB.getCinemasById(id);
	}
	
}
