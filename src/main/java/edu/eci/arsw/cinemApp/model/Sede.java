package edu.eci.arsw.cinemApp.model;

import java.util.List;

public class Sede {

	public String nombre;
	public String ciudad;
	public String ubicacion;
	public String horario;
	public String id;
	public List salas;
	
	public Sede(String nombre, String ciudad, String ubicacion, String horario, String id) {
		
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.ubicacion = ubicacion;
		this.horario = horario;
		this.id = id;
		
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

	public List getSalas() {
		return salas;
	}

	public void setSalas(List salas) {
		this.salas = salas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
