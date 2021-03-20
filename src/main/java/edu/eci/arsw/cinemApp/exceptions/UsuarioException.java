package edu.eci.arsw.cinemApp.exceptions;

public class UsuarioException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public static String USUARIO_REGISTRADO ="El usuario ya existe";
    public static String CORREO_REGISTRADO ="EL correo ya existe";
    public static String USUARIO_INVALIDO ="El usuario no es valido";
    public static String CORREO_INVALIDO ="Correo no es valido";
    
    public UsuarioException(String message) {
    	super(message);
    }
    
}
