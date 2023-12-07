package com.app.TP_DESI2023.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.TP_DESI2023.Entitys.Asiento;
import com.app.TP_DESI2023.Repositorys.AsientoRepository;
import com.app.TP_DESI2023.Repositorys.AvionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class AsientoServiceImp implements AsientoService {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AvionRepository avionRepository;
	
	@Autowired 
	private AsientoRepository asientoRepository;

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
		List<Asiento> asientosLibres = new ArrayList<>();
		for (int i = 0; i < asientos.size(); i++) {
			if (asientos.get(i).isReservado() == false) {
				asientosLibres.add(asientos.get(i));
			}
		}
		return asientosLibres;
	}

	@Override
	public Optional<Asiento> findById(Long id) {
		return asientoRepository.findById(id);
	}

	@Override
	public Asiento actualizarAsiento(Asiento asiento) {
		return asientoRepository.save(asiento);
	}
	

}
