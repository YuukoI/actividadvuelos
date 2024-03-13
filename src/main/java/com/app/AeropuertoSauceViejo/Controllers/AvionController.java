package com.app.AeropuertoSauceViejo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.AeropuertoSauceViejo.Entitys.Avion;
import com.app.AeropuertoSauceViejo.Services.AvionService;

@Controller
public class AvionController {

	@Autowired
	private AvionService avionService;

	@GetMapping("/aviones")
	public String listaAviones(Model model) {
		model.addAttribute("aviones", avionService.obtenerAviones());
		return "aviones";
	}

	@PostMapping("/aviones")
	public String crearAvion(@ModelAttribute("avion") Avion avion) {
		Avion avionGuardar = new Avion(avion.getNombre(), avion.getNroFilas(), avion.getNroSillasFila());
		avionService.guardarAvion(avionGuardar);
		return "redirect:/aviones";
	}

	@GetMapping("/crear_avion")
	public String formularioCrearAvion(Model model) {
		Avion avion = new Avion();
		model.addAttribute(avion);
		return "crear_avion";
	}

}
