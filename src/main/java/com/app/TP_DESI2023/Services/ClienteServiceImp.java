package com.app.TP_DESI2023.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.TP_DESI2023.Entitys.Ciudad;
import com.app.TP_DESI2023.Entitys.Cliente;
import com.app.TP_DESI2023.Repositorys.ClienteRepository;
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

