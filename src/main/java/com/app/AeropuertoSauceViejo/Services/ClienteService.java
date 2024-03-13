package com.app.AeropuertoSauceViejo.Services;

import java.util.List;

import com.app.AeropuertoSauceViejo.Entitys.Cliente;

public interface ClienteService {

	Cliente guardarCliente(Cliente cliente);

	Cliente editarCliente(Cliente cliente);

	void borrarCliente(Long id);

	List<Cliente> obtenerClientes();

	Cliente obtenerClientePorDni(int dni);

}
