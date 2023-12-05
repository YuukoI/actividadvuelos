package com.app.TP_DESI2023.Controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.TP_DESI2023.Entitys.Cliente;
import com.app.TP_DESI2023.Entitys.Vuelo;
import com.app.TP_DESI2023.Exceptions.CustomException;
import com.app.TP_DESI2023.Services.AsientoService;
import com.app.TP_DESI2023.Services.ClienteService;
import com.app.TP_DESI2023.Services.PasajeService;
import com.app.TP_DESI2023.Services.VueloService;

@Controller
public class PasajeController {
	
	@Autowired
	private PasajeService pasajeService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private VueloService vueloService;
	
	@Autowired
	private AsientoService asientoService;
	
    @GetMapping("/validacion_cliente")
    public String mostrarPaginaValidacionCliente() {
        return "validacion_cliente";
    }

    @PostMapping("/validacion_cliente")
    public String validarCliente(@RequestParam("dni") int dni) throws CustomException {
    	Cliente c = clienteService.obtenerClientePorDni(dni);
  
		  if(c != null) {
			  return "redirect:/pasajes?dni=" + dni;
		  }
		  
		  throw new CustomException("No existe el cliente.");
    	
    }

    @GetMapping("/pasajes")
    public String mostrarPaginaPasajes(@RequestParam(name = "dni", required = false) int dni, Model model) {
    	Cliente c = clienteService.obtenerClientePorDni(dni);
    	
    		model.addAttribute("cliente", c);
    		model.addAttribute("vuelos", vueloService.obtenerVuelos());

        return "pasajes";
    }
    
    
}


