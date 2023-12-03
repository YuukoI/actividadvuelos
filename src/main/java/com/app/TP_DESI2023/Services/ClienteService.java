package com.app.TP_DESI2023.Services;

import java.util.List;

import com.app.TP_DESI2023.Entitys.Cliente;

public interface ClienteService {

	Cliente guardarCliente(Cliente cliente);

	Cliente editarCliente(Cliente cliente);

	void borrarCliente(Long id);

	List<Cliente> obtenerClientes();

	Cliente obtenerClientePorDni(int dni);

}
