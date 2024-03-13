package com.app.TP_DESI2023.Services;

import java.util.Optional;

import com.app.TP_DESI2023.Entitys.Pasaje;

public interface PasajeService {

	Pasaje crearPasaje(Pasaje pasaje);

	Optional<Pasaje> findById(Long id);

}
