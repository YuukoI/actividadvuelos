package com.app.TP_DESI2023.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.TP_DESI2023.Entitys.Pasaje;
import com.app.TP_DESI2023.Repositorys.PasajeRepository;

@Service
public class PasajeServiceImp implements PasajeService{
	
	@Autowired 
	private PasajeRepository pasajeRepository;
	
	@Override
	public Pasaje crearPasaje(Pasaje pasaje) {
		return pasajeRepository.save(pasaje);
	}

	@Override
	public Optional<Pasaje> findById(Long id) {
		return pasajeRepository.findById(id);
	}
	

}
