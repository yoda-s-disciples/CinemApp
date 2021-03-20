package edu.eci.arsw.cinemApp.model;

import java.util.List;

public class Pelicula {

	public String nombre;
	public String duracion;
	public String calificacion;
	public String horario;
	public String genero;
	public String id;
	public String poster;
	public String director;
	public List usuarios;
	public List cinemas;

	public Pelicula(String nombre, String duracion, String calificacion, String horario, String genero, String id, String poster, String director) {

		this.nombre = nombre;
		this.duracion = duracion;
		this.calificacion = calificacion;
		this.horario = horario;
		this.genero = genero;
		this.id = id;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List usuarios) {
		this.usuarios = usuarios;
	}

	public List getCinemas() {
		return cinemas;
	}

	public void setCinemas(List cinemas) {
		this.cinemas = cinemas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
}
