package edu.eci.arsw.cinemApp.exceptions;

public class PeliculaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	public static String PELICULA_INVALIDA ="La pelicula es invalida";
    
    public PeliculaException(String message) {
    	super(message);
    }

}