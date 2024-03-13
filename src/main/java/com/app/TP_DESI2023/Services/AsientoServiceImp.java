package com.app.TP_DESI2023.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.TP_DESI2023.Entitys.Asiento;
import com.app.TP_DESI2023.Repositorys.AsientoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class AsientoServiceImp implements AsientoService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AsientoRepository asientoRepository;

	@Override
	public int cantidadAsientosLibresPorVuelo(String nroVuelo) {
		String jpql = "SELECT COUNT(a) FROM Asiento a WHERE a.reservado = false AND a.vuelo.nroVuelo = :nroVuelo";

		TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
		query.setParameter("nroVuelo", nroVuelo);
		return query.getSingleResult().intValue();
	}

	@Override
	public Optional<Asiento> findById(Long id) {
		return asientoRepository.findById(id);
	}

	@Override
	public Asiento actualizarAsiento(Asiento asiento) {
		return asientoRepository.save(asiento);
	}

	@Override
	public List<Asiento> crearAsientos(List<Asiento> asientos) {
		return asientoRepository.saveAll(asientos);
	}

	@Override
	public List<Asiento> obtenerAsientosLibresPorVuelo(String nroVuelo) {
		String jpql = "SELECT a FROM Asiento a WHERE a.reservado = false AND a.vuelo.nroVuelo = :nroVuelo";

		TypedQuery<Asiento> query = entityManager.createQuery(jpql, Asiento.class);
		query.setParameter("nroVuelo", nroVuelo);

		List<Asiento> asientosDisponibles = query.getResultList();

		return asientosDisponibles;
	}

}
