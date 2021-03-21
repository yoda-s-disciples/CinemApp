package edu.eci.arsw.cinemApp.model;

import java.util.List;

public class Usuario {
	
	public String username;
	public String nombre;
	public String apellido;
	public String correo;
	public String password;
	public List peliculas;
	public List salas;

	public Usuario(String username, String nombre, String apellido, String correo, String password) {
		
		this.username = username;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.password = password;

	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List peliculas) {
		this.peliculas = peliculas;
	}

	public List getSalas() {
		return salas;
	}

	public void setSalas(List salas) {
		this.salas = salas;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.getCorreo() == ((Usuario) o).getCorreo();
    }

}
