package com.app.TP_DESI2023.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
  
  
	
	/*	
	 * 
	*/
}
