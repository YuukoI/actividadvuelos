package com.app.TP_DESI2023.Entitys;

import java.time.*;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "vuelos")
public class Vuelo {
	@Id
	private String nroVuelo;
	
	@ManyToOne
	private Ciudad ciudadOrigen;
	
	@ManyToOne
	private Ciudad ciudadDestino;
	
	private String tipoVuelo;
	
	private double precioBruto; 
	
	@DateTimeFormat
	private LocalDateTime fechaHora;
	
	@ManyToOne
	private Avion avion;
	
	private String estado; 
	
	private int asientosDisponibles;

	public Vuelo() {
		
	}

	public Vuelo(String nroVuelo, Ciudad ciudadOrigen, Ciudad ciudadDestino, String tipoVuelo, double precioBruto,
			LocalDateTime fechaHora, Avion avion, String estado, int asientosDisponibles) {
		super();
		this.nroVuelo = nroVuelo;
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.tipoVuelo = tipoVuelo;
		this.precioBruto = precioBruto;
		this.fechaHora = fechaHora;
		this.avion = avion;
		this.estado = estado;
		this.asientosDisponibles = asientosDisponibles;
	}

	public String getNroVuelo() {
		return nroVuelo;
	}


	public void setNroVuelo(String nroVuelo) {
		this.nroVuelo = nroVuelo;
	}


	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}


	public void setCiudadOrigen(Ciudad ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}


	public Ciudad getCiudadDestino() {
		return ciudadDestino;
	}


	public void setCiudadDestino(Ciudad ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}


	public String getTipoVuelo() {
		return tipoVuelo;
	}


	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}


	public double getPrecioBruto() {
		return precioBruto;
	}


	public void setPrecioBruto(double precioBruto) {
		this.precioBruto = precioBruto;
	}


	public LocalDateTime getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}


	public Avion getAvion() {
		return avion;
	}


	public void setAvion(Avion avion) {
		this.avion = avion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Vuelo [nroVuelo=" + nroVuelo + ", ciudadOrigen=" + ciudadOrigen + ", ciudadDestino=" + ciudadDestino
				+ ", tipoVuelo=" + tipoVuelo + ", precioBruto=" + precioBruto + ", fechaHora=" + fechaHora + ", avion="
				+ avion + ", estado=" + estado + "]";
	}

	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}
	
}