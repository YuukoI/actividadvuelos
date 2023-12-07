package com.app.TP_DESI2023.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.TP_DESI2023.Entitys.Impuesto;
import com.app.TP_DESI2023.Services.ImpuestoService;

@Controller
public class ImpuestoController {

	@Autowired
	private ImpuestoService impuestoService;
	
	@GetMapping("/impuestos")
	public String listaImpuestos(Model model) {
		model.addAttribute("impuestos", impuestoService.obtenerImpuestos());
		return "impuestos";
	}
	
	@GetMapping("impuestos/editar/{id}")
	public String editarImpuesto(@PathVariable Long id, Model model) {
		if(impuestoService.obtenerImpuestoPorId(id).get().getNombre().equalsIgnoreCase("IVA")) {
			model.addAttribute("impuesto", impuestoService.obtenerImpuestoPorId(id).get());
			return "modificaciones_impuestos";
		}
		model.addAttribute("impuesto", impuestoService.obtenerImpuestoPorId(id).get());
		return "modificaciones_impuestos";
	}

	@PostMapping("/impuestos/{id}")
	public String actualizarImpuesto(@PathVariable Long id, @ModelAttribute("impuesto") Impuesto impuesto, Model model) {
		Optional<Impuesto> impuestoOptional = impuestoService.obtenerImpuestoPorId(id);
		impuesto.setId(impuestoOptional.get().getId());
		impuesto.setNombre(impuestoOptional.get().getNombre());
		impuesto.setUnidadMedida(impuestoOptional.get().getUnidadMedida());
		impuestoService.editarImpuesto(impuesto);
		return "redirect:/impuestos";
	}
	
}
