package com.app.TP_DESI2023.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.TP_DESI2023.Entitys.Vuelo;
import com.app.TP_DESI2023.Exceptions.CustomException;
import com.app.TP_DESI2023.Services.AvionService;
import com.app.TP_DESI2023.Services.CiudadService;
import com.app.TP_DESI2023.Services.ImpuestoService;
import com.app.TP_DESI2023.Services.VueloService;

import org.springframework.ui.Model;

@Controller
public class VueloController {
	
  @Autowired
  private VueloService vueloService;
  
  @Autowired 
  private CiudadService ciudadService;
  
  @Autowired
  private AvionService avionService;

  
  @GetMapping("/vuelos")
  public String listaVuelos(@RequestParam(name = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFiltro, Model model) {
	  List<Vuelo> vuelos;
	  
	  if(fechaFiltro != null) {
		  vuelos = vueloService.obtenerVuelosPorFecha(fechaFiltro);
	  } else {
		  vuelos = vueloService.ordenarPorFechaMasCercana();
	  }
	  
	  model.addAttribute("vuelos", vuelos);
      return "vuelos";
  }
  
  @GetMapping("/crear_vuelo")
  public String formularioCrearVuelo(Model model) {
      model.addAttribute("vuelo", new Vuelo());
      model.addAttribute("ciudades", ciudadService.obtenerCiudades());
      model.addAttribute("aviones", avionService.obtenerAviones());
      return "crear_vuelo";
  }

  @PostMapping("/crear_vuelo")
  public String crearVuelo(@ModelAttribute("vuelo") Vuelo vuelo) throws CustomException {
	  Optional<Vuelo> vueloOptional = vueloService.obtenerVueloPorNro(vuelo.getNroVuelo());

      LocalDateTime localDateTime = vuelo.getFechaHora();
      LocalDate soloFecha = localDateTime.toLocalDate();
	  
	  if(vueloOptional.isPresent()) {
		  throw new CustomException("Ya existe un vuelo con este Numero, ingrese otro!");
	  }
	  
	  List<Vuelo> vuelosFecha = vueloService.obtenerVuelosPorFecha(soloFecha);
	  if(!vuelosFecha.isEmpty()) {
		  for(Vuelo v: vuelosFecha ) {
			  if(v.getAvion() == vuelo.getAvion()) {
				  throw new CustomException("Ya existe un vuelo para ese dia con este avion!");
			  }
		  }
	  }
	  vuelo.setEstado("NORMAL");
      vueloService.guardarVuelo(vuelo);
      return "redirect:/vuelos";
  }
  
	@GetMapping("vuelos/editar/{nroVuelo}")
	public String editarVuelo(@PathVariable String nroVuelo, Model model) {
		model.addAttribute("vuelo", vueloService.obtenerVueloPorNro(nroVuelo).get());
	    model.addAttribute("ciudades", ciudadService.obtenerCiudades());
	    model.addAttribute("aviones", avionService.obtenerAviones());
		return "modificaciones_vuelos";
	}

	@PostMapping("/vuelos/{nroVuelo}")
	public String actualizarVuelo(@PathVariable String nroVuelo, @ModelAttribute("vuelo") Vuelo vuelo, Model model) {
		Optional<Vuelo> vueloOptional = vueloService.obtenerVueloPorNro(nroVuelo);
		vuelo.setNroVuelo(vueloOptional.get().getNroVuelo());
		vueloService.editarVuelo(vuelo);

		return "redirect:/vuelos";
	}

	@GetMapping("/vuelos/{nroVuelo}")
	public String borrarAvion(@PathVariable String nroVuelo) {
		vueloService.borrarVuelo(nroVuelo);
		return "redirect:/vuelos";
	}
  
}
