package edu.eci.arsw.cinemApp.exceptions;

public class SedeException extends Exception{
	private static final long serialVersionUID = 1L;
	public static String SEDE_INVALIDA ="La sede es invalida";
    
    public SedeException(String message) {
    	super(message);
    }

}
