package com.app.TP_DESI2023.Entitys;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "avion", cascade = CascadeType.ALL)
	private List<Asiento> asientos;
	
    public Avion() {
    }

    public Avion(String nombre,int nroFilas, int nroSillasFila) {
    	this.nombre = nombre;
        this.nroFilas = nroFilas;
        this.nroSillasFila = nroSillasFila;
        this.asientos = generarAsientos();
    }
    
    private List<Asiento> generarAsientos() {
        List<Asiento> nuevosAsientos = new ArrayList<>();
        for (int fila = 1; fila <= nroFilas; fila++) {
            for (int numeroSilla = 1; numeroSilla <= nroSillasFila; numeroSilla++) {
                nuevosAsientos.add(new Asiento(fila, numeroSilla, false, this));
            }
        }
        return nuevosAsientos;
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

	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}
	
}
