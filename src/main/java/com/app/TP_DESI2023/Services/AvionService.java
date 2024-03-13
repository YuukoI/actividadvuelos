package com.app.TP_DESI2023.Services;

import java.util.List;
import java.util.Optional;

import com.app.TP_DESI2023.Entitys.Avion;

public interface AvionService {

	Avion guardarAvion(Avion avion);

	Avion editarAvion(Avion avion);

	void borrarAvion(Long avionId);

	List<Avion> obtenerAviones();

	Optional<Avion> obtenerAvionPorId(Long avionId);
}
