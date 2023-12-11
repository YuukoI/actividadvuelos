package com.app.TP_DESI2023.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.TP_DESI2023.Entitys.Cliente;
import com.app.TP_DESI2023.Services.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/clientes")
	public String listaClientes(Model model) {
		model.addAttribute("clientes", clienteService.obtenerClientes());
		return "clientes";
	}

	@PostMapping("/clientes")
	public String crearCliente(@ModelAttribute("cliente") Cliente cliente) {
		clienteService.guardarCliente(cliente);
		return "redirect:/clientes";
	}

	@GetMapping("/crear_cliente")
	public String formularioCrearCliente(Model model) {
		Cliente crear_cliente = new Cliente();
		model.addAttribute(crear_cliente);
		return "crear_cliente";
	}

	@GetMapping("clientes/editar/{dni}")
	public String editarCliente(@PathVariable int dni, Model model) {
		model.addAttribute("cliente", clienteService.obtenerClientePorDni(dni));
		return "modificaciones_clientes";
	}

	@PostMapping("/clientes/{dni}")
	public String actualizarCliente(@PathVariable int dni, @ModelAttribute("cliente") Cliente cliente, Model model) {
		Cliente clienteBuscado = clienteService.obtenerClientePorDni(dni);
		
		cliente.setId(clienteBuscado.getId());
		clienteService.editarCliente(cliente);
		return "redirect:/clientes";
	}

	@GetMapping("/clientes/{id}")
	public String borrarCliente(@PathVariable Long id) {
		clienteService.borrarCliente(id);
		return "redirect:/clientes";
	}
}
