package edu.eci.arsw.cinemApp.exceptions;

public class CinemaException extends Exception{
	private static final long serialVersionUID = 1L;
	public static String PELICULA_INVALIDA ="La pelicula es invalida";
    
    public CinemaException(String message) {
    	super(message);
    }

}
