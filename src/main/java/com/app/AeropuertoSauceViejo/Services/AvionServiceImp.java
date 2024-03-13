package com.app.AeropuertoSauceViejo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.AeropuertoSauceViejo.Entitys.Avion;
import com.app.AeropuertoSauceViejo.Repositorys.AvionRepository;

@Service
public class AvionServiceImp implements AvionService {

	@Autowired
	private AvionRepository avionRepository;

	@Override
	public Avion guardarAvion(Avion avion) {
		return avionRepository.save(avion);
	}

	@Override
	public Avion editarAvion(Avion avion) {
		return avionRepository.save(avion);
	}

	@Override
	public void borrarAvion(Long avionId) {
		avionRepository.deleteById(avionId);
	}

	@Override
	public List<Avion> obtenerAviones() {
		return avionRepository.findAll();
	}

	@Override
	public Optional<Avion> obtenerAvionPorId(Long avionId) {
		return avionRepository.findById(avionId);
	}
}
