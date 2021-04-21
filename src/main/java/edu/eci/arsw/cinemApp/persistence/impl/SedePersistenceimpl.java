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
	public List<Sede> getSedeByID(String id) throws SedeException {
		return cineMapDB.getSedeByID(id);
	}

}
