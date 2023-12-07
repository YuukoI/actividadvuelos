package com.app.TP_DESI2023.Services;

import java.util.List;
import java.util.Optional;

import com.app.TP_DESI2023.Entitys.Asiento;

public interface AsientoService {

	int cantidadAsientosLibresPorVuelo(Long avionId);
	
	List<Asiento> obtenerAsientosLibresPorAvion(Long avionId);
	Optional <Asiento> findById(Long id);
	
	Asiento actualizarAsiento(Asiento asiento);
}
