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
	
	@RequestMapping(path = "/reserva/{pelicula}/{cinema}/{sede}", method = RequestMethod.GET)
	public ResponseEntity<?> getSedeReserva(@PathVariable("pelicula") String pelicula, @PathVariable("cinema") String cinema, @PathVariable("sede") String sede){
		try {
			return new ResponseEntity<>(sedeServices.getSedeReserva(pelicula, cinema, sede), HttpStatus.OK);
		}catch(SedeException e) {
			e.printStackTrace();
			return new ResponseEntity<>("404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/asientos/{pelicula}/{cinema}/{sede}/{username}/{asientos}", method = RequestMethod.POST)
	public ResponseEntity<?> comprarAsiento(@PathVariable("pelicula") String pelicula, @PathVariable("cinema") String cinema, @PathVariable("sede") String sede, @PathVariable("username") String username, @PathVariable("asientos") String asientos){
		try {
			sedeServices.comprarAsiento(pelicula, cinema, sede, username, asientos);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch(SedeException e) {
			System.out.println("entro error");
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.ALREADY_REPORTED);
		}
	}
	
	@RequestMapping(path = "/reservas/{user}", method = RequestMethod.GET)
	public ResponseEntity<?> getReservasByUser(@PathVariable("user") String user){
		try {
			return new ResponseEntity<>(sedeServices.getReservasByUser(user), HttpStatus.OK);
		}catch(SedeException e) {
			e.printStackTrace();
			return new ResponseEntity<>("404 NOT FOUND", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/delete/{pelicula}/{cinema}/{sede}/{username}/{asientos}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAsiento(@PathVariable("pelicula") String pelicula, @PathVariable("cinema") String cinema, @PathVariable("sede") String sede, @PathVariable("username") String username, @PathVariable("asientos") String asientos){
		try {
			System.out.print("entro controller");
			sedeServices.deleteAsiento	(pelicula, cinema, sede, username, asientos);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}catch(SedeException e) {
			System.out.println("entro error");
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
		}
	}
	
}
