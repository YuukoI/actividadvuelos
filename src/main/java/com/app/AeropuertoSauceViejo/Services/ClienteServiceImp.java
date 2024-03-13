package com.app.AeropuertoSauceViejo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.AeropuertoSauceViejo.Entitys.Cliente;
import com.app.AeropuertoSauceViejo.Repositorys.ClienteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class ClienteServiceImp implements ClienteService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente editarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void borrarCliente(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public List<Cliente> obtenerClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente obtenerClientePorDni(int dni) {
		String jpql = "SELECT c FROM Cliente c WHERE c.dni = :dni";
		Query query = entityManager.createQuery(jpql, Cliente.class);
		query.setParameter("dni", dni);

		try {
			return (Cliente) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
