package edu.eci.arsw.cinemApp.model;


public class Sala {
	
	public int fila;
	public int columna;
	public int puestosTotales;
	public int puestosDisponibles;
	public int puestosOcupados;
	public String id;
	
	public Sala(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public int getPuestosTotales() {
		return puestosTotales;
	}
	public void setPuestosTotales(int puestosTotales) {
		this.puestosTotales = puestosTotales;
	}
	public int getPuestosDisponibles() {
		return puestosDisponibles;
	}
	public void setPuestosDisponibles(int puestosDisponibles) {
		this.puestosDisponibles = puestosDisponibles;
	}
	public int getPuestosOcupados() {
		return puestosOcupados;
	}
	public void setPuestosOcupados(int puestosOcupados) {
		this.puestosOcupados = puestosOcupados;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
