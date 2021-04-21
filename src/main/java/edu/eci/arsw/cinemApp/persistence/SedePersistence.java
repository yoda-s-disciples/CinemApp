package edu.eci.arsw.cinemApp.persistence;

import java.util.List;

import edu.eci.arsw.cinemApp.exceptions.SedeException;
import edu.eci.arsw.cinemApp.model.Sede;

public interface SedePersistence {

	public List<Sede> getSede() throws SedeException;

	public List<Sede> getSedeByID(String id) throws SedeException;

}
