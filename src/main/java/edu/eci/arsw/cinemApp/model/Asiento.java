package edu.eci.arsw.cinemApp.model;

public class Asiento {
	
	public String fila;
	public int numero;
	public String tipo;
	
	public Asiento(String fila, int numero, String tipo) {
		
		this.fila = fila;
		this.numero = numero;
		this.tipo = tipo;
		
	}

	public String getFila() {
		return fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
