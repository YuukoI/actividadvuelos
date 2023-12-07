package com.app.TP_DESI2023.Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aviones")
public class Avion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private int nroFilas;
	private int nroSillasFila;
	private int cantidadSillas;
	
    public Avion() {
    }

    public Avion(String nombre,int nroFilas, int nroSillasFila) {
    	this.nombre = nombre;
        this.nroFilas = nroFilas;
        this.nroSillasFila = nroSillasFila;
        this.cantidadSillas = nroFilas * nroSillasFila;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNroFilas() {
		return nroFilas;
	}

	public void setNroFilas(int nroFilas) {
		this.nroFilas = nroFilas;
	}

	public int getNroSillasFila() {
		return nroSillasFila;
	}

	public void setNroSillasFila(int nroSillasFila) {
		this.nroSillasFila = nroSillasFila;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadSillas() {
		return cantidadSillas;
	}

	public void setCantidadSillas(int cantidadSillas) {
		this.cantidadSillas = cantidadSillas;
	}


	
}
