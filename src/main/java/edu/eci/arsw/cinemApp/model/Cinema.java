package edu.eci.arsw.cinemApp.model;

import java.util.List;

public class Cinema {
	
	public String nombre;
	public List sedes;
	public String id;
	
	public Cinema(String nombre, String id) {
		this.nombre = nombre;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List getSedes() {
		return sedes;
	}

	public void setSedes(List sedes) {
		this.sedes = sedes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
