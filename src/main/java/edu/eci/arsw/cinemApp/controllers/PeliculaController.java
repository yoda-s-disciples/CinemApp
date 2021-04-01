package edu.eci.arsw.cinemApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.cinemApp.exceptions.PeliculaException;
import edu.eci.arsw.cinemApp.exceptions.UsuarioException;
import edu.eci.arsw.cinemApp.model.Pelicula;
import edu.eci.arsw.cinemApp.services.PeliculaServices;

@RestController
@RequestMapping(value = "/pelicula")
public class PeliculaController {
	
	@Autowired
	private PeliculaServices peliculaServices;
	
	@RequestMapping(path = "/Movies", method = RequestMethod.GET)
	public ResponseEntity<?> getPeliculas(){
		try {
			System.out.println("Controller");
			return new ResponseEntity<>(peliculaServices.getPeliculas(), HttpStatus.OK);
		}catch(PeliculaException e) {
			System.out.println("Controller else");
			e.printStackTrace();
            return new ResponseEntity<>("404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}

}
