package edu.eci.arsw.cinemApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.cinemApp.exceptions.PeliculaException;
import edu.eci.arsw.cinemApp.exceptions.SedeException;
import edu.eci.arsw.cinemApp.model.Sala;
import edu.eci.arsw.cinemApp.model.Sede;
import edu.eci.arsw.cinemApp.persistence.SedePersistence;

@Service
public class SedeServices {
	
	@Autowired
	private SedePersistence sedePersistence;

	public List<Sede> getSede() throws SedeException{
		return sedePersistence.getSede();
	}

	public List<Sede> getSedeByID(String idPelicula, String idCinema) throws SedeException{
		return sedePersistence.getSedeByID(idPelicula, idCinema);
	}

	public void comprarAsiento(String pelicula, String cinema, String sede, String username, String asientos) throws SedeException{
		sedePersistence.comprarAsiento(pelicula, cinema, sede, username, asientos);
	}

	public List<Sede> getSedeReserva(String pelicula, String cinema, String sede) throws SedeException{
		return sedePersistence.getSedeReserva(pelicula, cinema, sede);
	}

	public Object getReservasByUser(String user) throws SedeException{
		return sedePersistence.getReservasByUser(user);
	}

	public void deleteAsiento(String pelicula, String cinema, String sede, String username, String asientos) throws SedeException{
		sedePersistence.deleteAsiento(pelicula, cinema, sede, username, asientos);
	}

}
