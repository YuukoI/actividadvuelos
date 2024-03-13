package com.app.TP_DESI2023.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginaPrincipalController {

	@GetMapping
	public String home() {
		return "pagina_principal";
	}

	@RequestMapping("/impuestos")
	public String mostrarMenuImpuestos() {
		return "impuestos";
	}

	@RequestMapping("/clientes")
	public String mostrarMenuClientes() {
		return "clientes";
	}

	@RequestMapping("/aviones")
	public String mostrarMenuAviones() {
		return "aviones";
	}

	@RequestMapping("/validacion_cliente")
	public String mostrarMenuValidacionCliente() {
		return "aviones";
	}

	@RequestMapping("/ciudades")
	public String mostrarMenuCiudades() {
		return "ciudades";
	}

	@RequestMapping("/vuelos")
	public String mostrarMenuVuelos() {
		return "aviones";
	}
}
