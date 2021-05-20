package edu.eci.arsw.cinemApp.persistence;

import java.util.List;

import edu.eci.arsw.cinemApp.exceptions.SedeException;
import edu.eci.arsw.cinemApp.model.Sede;

public interface SedePersistence {

	public List<Sede> getSede() throws SedeException;

	public List<Sede> getSedeByID(String idPelicula, String idCinema) throws SedeException;

	public void comprarAsiento(String pelicula, String cinema, String sede, String username, String asientos) throws SedeException;

	public List<Sede> getSedeReserva(String pelicula, String cinema, String sede)throws SedeException;

	public Object getReservasByUser(String user);

	public void deleteAsiento(String pelicula, String cinema, String sede, String username, String asientos) throws SedeException;

}
