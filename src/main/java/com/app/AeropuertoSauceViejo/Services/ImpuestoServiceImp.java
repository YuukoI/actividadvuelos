package com.app.AeropuertoSauceViejo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.AeropuertoSauceViejo.Entitys.Impuesto;
import com.app.AeropuertoSauceViejo.Repositorys.ImpuestoRepository;

@Service
public class ImpuestoServiceImp implements ImpuestoService {

	@Autowired
	private ImpuestoRepository impuestoRepository;

	@Override
	public Impuesto editarImpuesto(Impuesto impuesto) {
		return impuestoRepository.save(impuesto);
	}

	@Override
	public List<Impuesto> obtenerImpuestos() {
		return impuestoRepository.findAll();
	}

	@Override
	public Optional<Impuesto> obtenerImpuestoPorId(Long impuestoId) {
		return impuestoRepository.findById(impuestoId);
	}

}
