package com.app.TP_DESI2023.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.app.TP_DESI2023.Entitys.Vuelo;
import com.app.TP_DESI2023.Exceptions.CustomException;
import com.app.TP_DESI2023.Services.AvionService;
import com.app.TP_DESI2023.Services.CiudadService;
import com.app.TP_DESI2023.Services.VueloService;

import org.springframework.ui.Model;

@Controller
public class VueloController {
  @Autowired
  private VueloService vueloService;
   
  @GetMapping("/vuelos")
  public String listaVuelos(Model model) {
	  model.addAttribute("vuelos", vueloService.obtenerVuelos());
      return "vuelos";
  }
  
  @Autowired 
  private CiudadService ciudadService;
  
  @Autowired
  private AvionService avionService;
  
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
  
}
