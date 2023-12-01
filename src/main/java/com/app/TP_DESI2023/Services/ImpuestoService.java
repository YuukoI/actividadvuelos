package com.app.TP_DESI2023.Services;

import java.util.List;
import java.util.Optional;

import com.app.TP_DESI2023.Entitys.Impuesto;

public interface ImpuestoService {

	Impuesto editarImpuesto(Impuesto impuesto);
	
	List<Impuesto> obtenerImpuestos();
	
	Optional<Impuesto> obtenerImpuestoPorId(Long impuestoId);
}
