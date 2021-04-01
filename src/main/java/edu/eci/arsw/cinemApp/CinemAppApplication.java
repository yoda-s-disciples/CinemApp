package edu.eci.arsw.cinemApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal 
 * @author Camilo
 */
@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.cinemApp"})
public class CinemAppApplication {
	
	/**
	 * Metodo que ejecuta la aplicación web y realiza el reccorido.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CinemAppApplication.class, args);
	}
}
