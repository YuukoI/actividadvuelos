package com.app.TP_DESI2023.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.TP_DESI2023.Entitys.Ciudad;
import com.app.TP_DESI2023.Repositorys.CiudadRepository;

@Service
public class CiudadServiceImp implements CiudadService {

	@Autowired
	private CiudadRepository ciudadRepository;

	@Override
	public Ciudad guardarCiudad(Ciudad ciudad) {
		return ciudadRepository.save(ciudad);
	}

	@Override
	public Ciudad editarCiudad(Ciudad ciudad) {
		return ciudadRepository.save(ciudad);
	}

	@Override
	public void borrarCiudad(Long id) {
		ciudadRepository.deleteById(id);

	}

	@Override
	public List<Ciudad> obtenerCiudades() {
		return ciudadRepository.findAll();
	}

	@Override
	public Optional<Ciudad> obtenerCiudadPorId(Long id) {
		return ciudadRepository.findById(id);
	}

}
