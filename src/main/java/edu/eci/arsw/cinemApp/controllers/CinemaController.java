package edu.eci.arsw.cinemApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.cinemApp.exceptions.PeliculaException;
import edu.eci.arsw.cinemApp.services.CinemaServices;

@RestController
@RequestMapping(value = "/cinema")
public class CinemaController {
	
	@Autowired
	private CinemaServices cinemaServices;
	
	@RequestMapping(path = "/Cines", method = RequestMethod.GET)
	public ResponseEntity<?> getCinemas(){
		try {
			System.out.println("Controller");
			return new ResponseEntity<>(cinemaServices.getCinemas(), HttpStatus.OK);
		}catch(PeliculaException e) {
			System.out.println("Else");
			e.printStackTrace();
            return new ResponseEntity<>("404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
		
	}
}
