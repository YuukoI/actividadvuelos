package com.app.TP_DESI2023.Services;

import java.util.List;
import java.util.Optional;

import com.app.TP_DESI2023.Entitys.Ciudad;

public interface CiudadService {

	Ciudad guardarCiudad(Ciudad ciudad);

	Ciudad editarCiudad(Ciudad ciudad);

	void borrarCiudad(Long id);

	List<Ciudad> obtenerCiudades();

	Optional<Ciudad> obtenerCiudadPorId(Long id);
}
