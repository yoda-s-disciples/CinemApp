package edu.eci.arsw.cinemApp.controllers;

import org.springframework.stereotype.Controller;

/**
 * Controlador que permite responder a la peticion HTTP localhost:8080.
 * @author Camilo
 */
@Controller
public class cineAppController {
	public String getPage(){
		return "Hello World";
	}
}
