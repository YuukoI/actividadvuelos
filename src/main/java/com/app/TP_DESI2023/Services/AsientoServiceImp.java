package com.app.TP_DESI2023.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.TP_DESI2023.Entitys.Asiento;
import com.app.TP_DESI2023.Repositorys.AsientoRepository;
import com.app.TP_DESI2023.Repositorys.AvionRepository;

@Service
public class AsientoServiceImp implements AsientoService {

	@Autowired
	private AsientoRepository asientoRepository;

	@Autowired
	private AvionRepository avionRepository;

	@Override
	public int cantidadAsientosLibresPorVuelo(Long avionId) {
		List<Asiento> asientos = avionRepository.findById(avionId).get().getAsientos();
		int cont = 0;
		for (int i = 0; i < avionRepository.findById(avionId).get().getAsientos().size(); i++) {
			if (asientos.get(i).isReservado() == false) {
				cont += 1;
			}
		}
		return cont;
	}

	@Override
	public List<Asiento> obtenerAsientosLibresPorAvion(Long avionId) {
		List<Asiento> asientos = avionRepository.findById(avionId).get().getAsientos();
		List<Asiento> asientosLibres = avionRepository.findById(avionId).get().getAsientos();
		for (int i = 0; i < avionRepository.findById(avionId).get().getAsientos().size(); i++) {
			if (asientos.get(i).isReservado() == false) {
				asientosLibres.add(asientos.get(i));
			}
		}
		return asientosLibres;
	}
}
