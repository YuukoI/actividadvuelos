package com.app.TP_DESI2023.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.TP_DESI2023.Entitys.Avion;
import com.app.TP_DESI2023.Entitys.Ciudad;
import com.app.TP_DESI2023.Entitys.Vuelo;
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
  public String crearVuelo(@ModelAttribute("vuelo") Vuelo vuelo) {
      vueloService.guardarVuelo(vuelo);
      return "redirect:/vuelos";
  }
  
}
