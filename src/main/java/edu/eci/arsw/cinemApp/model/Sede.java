package edu.eci.arsw.cinemApp.model;

import java.util.List;

public class Sede {

	public String nombre;
	public String ciudad;
	public String ubicacion;
	public String horario;
	public String id;
	public String idCinema;
	
	public Sede(String nombre, String ciudad, String ubicacion, String horario, String id, String idCinema) {
		
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.ubicacion = ubicacion;
		this.horario = horario;
		this.id = id;
		this.idCinema = idCinema;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCinema() {
		return idCinema;
	}

	public void setIdCinema(String idCinema) {
		this.idCinema = idCinema;
	}
	
}
