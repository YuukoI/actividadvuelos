package com.app.TP_DESI2023.Services;

import java.util.List;
import java.util.Optional;

import com.app.TP_DESI2023.Entitys.Vuelo;

public interface VueloService {
Vuelo guardarVuelo(Vuelo vuelo);
	
	Vuelo editarVuelo(Vuelo vuelo);
	
	void borrarVuelo(String nroVuelo);
	
	List<Vuelo> obtenerVuelos();
	
	Optional<Vuelo> obtenerVueloPorNro(String nroVuelo);
}


