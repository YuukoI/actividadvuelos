package com.app.TP_DESI2023.Services;

import java.util.List;

import com.app.TP_DESI2023.Entitys.Asiento;

public interface AsientoService {

	int cantidadAsientosLibresPorVuelo(Long avionId);
	
	List<Asiento> obtenerAsientosLibresPorAvion(Long avionId);
	
}
