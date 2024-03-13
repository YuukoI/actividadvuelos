package com.app.AeropuertoSauceViejo.Services;

import java.util.List;
import java.util.Optional;

import com.app.AeropuertoSauceViejo.Entitys.Impuesto;

public interface ImpuestoService {

	Impuesto editarImpuesto(Impuesto impuesto);

	List<Impuesto> obtenerImpuestos();

	Optional<Impuesto> obtenerImpuestoPorId(Long impuestoId);
}
