package com.app.AeropuertoSauceViejo.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.app.AeropuertoSauceViejo.Entitys.Vuelo;

public interface VueloService {

	Vuelo guardarVuelo(Vuelo vuelo);

	Vuelo editarVuelo(Vuelo vuelo);

	void borrarVuelo(String nroVuelo);

	List<Vuelo> obtenerVuelos();

	Optional<Vuelo> obtenerVueloPorNro(String nroVuelo);

	List<Vuelo> obtenerVuelosPorFecha(LocalDate fecha);

	List<Vuelo> obtenerVuelosPorOrigen(String origen);

	List<Vuelo> obtenerVuelosPorDestino(String destino);

	List<Vuelo> ordenarPorFechaMasCercana();

	List<Vuelo> filtrarVuelos(LocalDate fecha, Long origenId, Long destinoId, String tipoVuelo);
}
