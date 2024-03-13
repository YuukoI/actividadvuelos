package com.app.AeropuertoSauceViejo.Services;

import java.util.List;
import java.util.Optional;

import com.app.AeropuertoSauceViejo.Entitys.Asiento;

public interface AsientoService {

	List<Asiento> crearAsientos(List<Asiento> asientos);

	int cantidadAsientosLibresPorVuelo(String nroVuelo);

	List<Asiento> obtenerAsientosLibresPorVuelo(String nroVuelo);

	Optional<Asiento> findById(Long id);

	Asiento actualizarAsiento(Asiento asiento);
}
