package edu.eci.arsw.cinemApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.cinemApp.exceptions.SedeException;
import edu.eci.arsw.cinemApp.services.SedeServices;

@RestController
@RequestMapping(value = "/sede")
public class SedeController {
	
	@Autowired
	private SedeServices sedeServices;
	
	@RequestMapping(path = "/sedes", method = RequestMethod.GET)
	public ResponseEntity<?> getPeliculas(){
		try {
			return new ResponseEntity<>(sedeServices.getSede(), HttpStatus.OK);
		}catch(SedeException e) {
			e.printStackTrace();
            return new ResponseEntity<>("404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/sedes/{idPelicula}/{idCinema}", method = RequestMethod.GET)
	public ResponseEntity<?> getSedeByID(@PathVariable("idPelicula") String idPelicula, @PathVariable("idCinema") String idCinema){
		try {
			return new ResponseEntity<>(sedeServices.getSedeByID(idPelicula, idCinema), HttpStatus.OK);
		}catch(SedeException e) {
			e.printStackTrace();
			return new ResponseEntity<>("404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}
}
