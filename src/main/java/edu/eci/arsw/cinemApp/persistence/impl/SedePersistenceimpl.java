package edu.eci.arsw.cinemApp.persistence.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.eci.arsw.cinemApp.DB.CinemAppDB;
import edu.eci.arsw.cinemApp.exceptions.SedeException;
import edu.eci.arsw.cinemApp.model.Sala;
import edu.eci.arsw.cinemApp.model.Sede;
import edu.eci.arsw.cinemApp.persistence.SedePersistence;

@Service
public class SedePersistenceimpl implements SedePersistence{
	
	CinemAppDB cineMapDB = new CinemAppDB();

	@Override
	public List<Sede> getSede() throws SedeException {
		return cineMapDB.getSede();
	}

	@Override
	public List<Sede> getSedeByID(String idPelicula, String idCinema) throws SedeException {
		return cineMapDB.getSedeByID(idPelicula, idCinema);
	}

	@Override
	public void comprarAsiento(String pelicula, String cinema, String sede, String username, String asientos) throws SedeException {
		cineMapDB.comprarAsiento(pelicula, cinema, sede, username, asientos);
	}

	@Override
	public List<Sede> getSedeReserva(String pelicula, String cinema, String sede) throws SedeException {
		return cineMapDB.getSedeReserva(pelicula, cinema, sede);
	}

	@Override
	public Object getReservasByUser(String user) {
		return cineMapDB.getReservasByUser(user);
	}

	@Override
	public void deleteAsiento(String pelicula, String cinema, String sede, String username, String asientos) throws SedeException {
		cineMapDB.deleteAsiento(pelicula, cinema, sede, username, asientos);
	}

}
