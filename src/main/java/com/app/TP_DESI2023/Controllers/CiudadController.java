package com.app.TP_DESI2023.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.TP_DESI2023.Entitys.Ciudad;
import com.app.TP_DESI2023.Services.CiudadService;

@Controller
public class CiudadController {

	@Autowired
	private CiudadService ciudadService;

	@GetMapping("/ciudades")
	public String listaCiudades(Model model) {
		model.addAttribute("ciudades", ciudadService.obtenerCiudades());
		return "ciudades";
	}

	@PostMapping("/ciudades")
	public String crearCiudad(@ModelAttribute("ciudad") Ciudad ciudad) {
		ciudadService.guardarCiudad(ciudad);
		return "redirect:/ciudades";
	}

	@GetMapping("/crear_ciudad")
	public String formularioCrearAvion(Model model) {
		Ciudad ciudad = new Ciudad();
		model.addAttribute(ciudad);
		return "crear_ciudad";
	}

	@GetMapping("ciudades/editar/{id}")
	public String editarCiudad(@PathVariable Long id, Model model) {
		model.addAttribute("ciudad", ciudadService.obtenerCiudadPorId(id).get());
		return "modificaciones_ciudades";
	}

	@PostMapping("/ciudades/{id}")
	public String actualizarCiudad(@PathVariable Long id, @ModelAttribute("ciudad") Ciudad ciudad, Model model) {
		Optional<Ciudad> ciudadOptional = ciudadService.obtenerCiudadPorId(id);
		ciudad.setId(ciudadOptional.get().getId());
		ciudadService.editarCiudad(ciudad);

		return "redirect:/ciudades";
	}

	@GetMapping("/ciudades/{id}")
	public String borrarCiudad(@PathVariable Long id) {
		ciudadService.borrarCiudad(id);
		return "redirect:/ciudades";
	}
}
