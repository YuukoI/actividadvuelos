package com.app.TP_DESI2023.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.TP_DESI2023.Entitys.Vuelo;
import com.app.TP_DESI2023.Repositorys.VueloRepository;

@Service 
public class VueloServiceImp implements VueloService {
     
	@Autowired
	private VueloRepository vueloRepository; 
	
	@Override
	public Vuelo guardarVuelo(Vuelo vuelo) {
		return vueloRepository.save(vuelo);
	}

	@Override
	public Vuelo editarVuelo(Vuelo vuelo) {
		return vueloRepository.save(vuelo);
	}

	@Override
	public void borrarVuelo(String nroVuelo) {
		vueloRepository.deleteById(nroVuelo);
		
	}

	@Override
	public List<Vuelo> obtenerVuelos() {
		return vueloRepository.findAll();
	}

	@Override
	public Optional<Vuelo> obtenerVueloPorNro(String nroVuelo) {
		return vueloRepository.findById(nroVuelo);
	}
	
	
}
