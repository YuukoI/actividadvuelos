package com.app.TP_DESI2023.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.TP_DESI2023.Entitys.Vuelo;
import com.app.TP_DESI2023.Repositorys.VueloRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class VueloServiceImp implements VueloService {

	@PersistenceContext
	private EntityManager entityManager;

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

	@Override
	public List<Vuelo> obtenerVuelosPorFecha(LocalDate fecha) {
		String jpql = "SELECT v FROM Vuelo v WHERE DATE(v.fechaHora) = :fecha";

		TypedQuery<Vuelo> query = entityManager.createQuery(jpql, Vuelo.class);
		query.setParameter("fecha", fecha);

		return (List<Vuelo>) query.getResultList();

	}

	@Override
	public List<Vuelo> obtenerVuelosPorOrigen(String origen) {
		String jpql = "SELECT v FROM Vuelo v WHERE v.origen = :origen";

		TypedQuery<Vuelo> query = entityManager.createQuery(jpql, Vuelo.class);
		query.setParameter("origen", origen);

		return (List<Vuelo>) query.getResultList();
	}

	@Override
	public List<Vuelo> obtenerVuelosPorDestino(String destino) {
		String jpql = "SELECT v FROM Vuelo v WHERE v.destino = :destino";

		TypedQuery<Vuelo> query = entityManager.createQuery(jpql, Vuelo.class);
		query.setParameter("destino", destino);

		return (List<Vuelo>) query.getResultList();
	}

	@Override
	public List<Vuelo> ordenarPorFechaMasCercana() {
		String jpql = "SELECT v FROM Vuelo v ORDER BY v.fechaHora ASC";
		
		TypedQuery<Vuelo> query = entityManager.createQuery(jpql, Vuelo.class);
		
		return query.getResultList();
	}

	@Override
	public List<Vuelo> filtrarVuelos(LocalDate fecha, Long origenId, Long destinoId, String tipoVuelo) {
	    List<Vuelo> vuelos = new ArrayList<>();

	    if (fecha != null) {
	        vuelos = obtenerVuelosPorFecha(fecha);
	    } else {
	        vuelos = ordenarPorFechaMasCercana();
	    }

	    if (origenId != null) {
	        vuelos = vuelos.stream()
	            .filter(vuelo -> vuelo.getCiudadOrigen().getId().equals(origenId))
	            .collect(Collectors.toList());
	    }

	    if (destinoId != null) {
	        vuelos = vuelos.stream()
	            .filter(vuelo -> vuelo.getCiudadDestino().getId().equals(destinoId))
	            .collect(Collectors.toList());
	    }

	    if (tipoVuelo != null && !tipoVuelo.isEmpty()) {
	        vuelos = vuelos.stream()
	            .filter(vuelo -> vuelo.getTipoVuelo().equalsIgnoreCase(tipoVuelo))
	            .collect(Collectors.toList());
	    }

	    return vuelos;
	}
	
	
	

}
