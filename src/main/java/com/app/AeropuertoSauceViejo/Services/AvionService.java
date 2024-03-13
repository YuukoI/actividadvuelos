package com.app.AeropuertoSauceViejo.Services;

import java.util.List;
import java.util.Optional;

import com.app.AeropuertoSauceViejo.Entitys.Avion;

public interface AvionService {

	Avion guardarAvion(Avion avion);

	Avion editarAvion(Avion avion);

	void borrarAvion(Long avionId);

	List<Avion> obtenerAviones();

	Optional<Avion> obtenerAvionPorId(Long avionId);
}
