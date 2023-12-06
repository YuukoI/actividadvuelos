package com.app.TP_DESI2023.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.TP_DESI2023.Entitys.Avion;
import com.app.TP_DESI2023.Services.AvionService;

@Controller
public class AvionController {

	@Autowired
	private AvionService avionService;


	/*
	@GetMapping()
	public ResponseEntity<List<Avion>> obtenerTodosAviones() {
		return new ResponseEntity<>(avionService.obtenerAviones(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Avion> obtenerAvionPorId(@PathVariable Long id) {
		Optional<Avion> avionOptional = avionService.obtenerAvionPorId(id);

		if (!avionOptional.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(avionService.obtenerAvionPorId(id).get(), HttpStatus.OK);
	}

	@PostMapping("/cargaravion")
	public ResponseEntity<Avion> guardarAvion(@RequestBody Avion avion) {
		Avion avionGuardar = new Avion(avion.getNombre(),avion.getNroFilas(),avion.getNroSillasFila());
		return new ResponseEntity<>(avionService.guardarAvion(avionGuardar), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Avion> borrarAvion(@PathVariable Long id) {
		Optional<Avion> avionOptional = avionService.obtenerAvionPorId(id);

		if (!avionOptional.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		avionService.borrarAvion(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Avion> editarAvion(@PathVariable Long id, @RequestBody Avion avion) {
		Optional<Avion> avionOptional = avionService.obtenerAvionPorId(id);

		if (!avionOptional.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		avion.setId(avionOptional.get().getId());
		
		avionService.editarAvion(avion);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	 LO COMENTADO ERA PARA PROBAR CON POSTMAN */
	
	
	@GetMapping("/aviones")
	public String listaAviones(Model model) {
		model.addAttribute("aviones", avionService.obtenerAviones());
		return "aviones";
	}
	
	@PostMapping("/aviones")
	public String crearAvion(@ModelAttribute("avion") Avion avion) {
		Avion avionGuardar = new Avion(avion.getNombre(),avion.getNroFilas(),avion.getNroSillasFila());
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
