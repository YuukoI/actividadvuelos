package com.app.AeropuertoSauceViejo.Services;

import java.util.Optional;

import com.app.AeropuertoSauceViejo.Entitys.Pasaje;

public interface PasajeService {

	Pasaje crearPasaje(Pasaje pasaje);

	Optional<Pasaje> findById(Long id);

}
