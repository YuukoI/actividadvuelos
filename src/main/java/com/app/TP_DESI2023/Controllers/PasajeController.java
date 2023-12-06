package com.app.TP_DESI2023.Controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.TP_DESI2023.Entitys.Asiento;
import com.app.TP_DESI2023.Entitys.Avion;
import com.app.TP_DESI2023.Entitys.Cliente;
import com.app.TP_DESI2023.Entitys.Impuesto;
import com.app.TP_DESI2023.Entitys.Vuelo;
import com.app.TP_DESI2023.Exceptions.CustomException;
import com.app.TP_DESI2023.Services.AsientoService;
import com.app.TP_DESI2023.Services.ClienteService;
import com.app.TP_DESI2023.Services.ImpuestoService;
import com.app.TP_DESI2023.Services.PasajeService;
import com.app.TP_DESI2023.Services.VueloService;

import jakarta.servlet.http.HttpSession;

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
	
	@Autowired
	private ImpuestoService impuestoService; 
	
    @GetMapping("/validacion_cliente")
    public String mostrarPaginaValidacionCliente() {
        return "validacion_cliente";
    }

    @PostMapping("/validacion_cliente")
    public String validarCliente(@RequestParam("dni") int dni, HttpSession session) throws CustomException {
        Cliente c = clienteService.obtenerClientePorDni(dni);

        if (c != null) {
            session.setAttribute("dni", dni);
            return "redirect:/pasajes";
        }

        throw new CustomException("No existe el cliente.");
    }
    
    
    @GetMapping("/pasajes")
    public String mostrarPaginaPasajes(HttpSession session, Model model) {
        Integer dni = (Integer) session.getAttribute("dni");
        Cliente c = clienteService.obtenerClientePorDni(dni);
         
        model.addAttribute("cliente", c);
        model.addAttribute("vuelos", vueloService.obtenerVuelos());
    
        
        
        return "pasajes";
    }
    
    
    @PostMapping("/pasajes")
      public String procesarPasajes(@RequestParam(name = "vuelo") String nroVuelo, HttpSession session, Model model) {
      
        Integer dni = (Integer) session.getAttribute("dni");
        
        Optional<Vuelo> vueloOptional = vueloService.obtenerVueloPorNro(nroVuelo);
        session.setAttribute("nroVuelo", nroVuelo);
        Cliente c = clienteService.obtenerClientePorDni(dni);
        
        
        model.addAttribute("vueloSeleccionado", vueloOptional);
        model.addAttribute("dni", c.getDni());
        model.addAttribute("cliente", c);

        if (vueloOptional.isPresent()) {
            if (vueloOptional.get().getTipoVuelo().equalsIgnoreCase("NACIONAL")) {
            	
                return "redirect:/venta_pasaje_nacional";
            } else {
                return "redirect:/venta_pasaje_internacional";
            }
        }

        return "redirect:/pagina_de_error";
    }
    
    @GetMapping("/venta_pasaje_nacional")
    public String MostrarventaPasajesNacionales(HttpSession session, Model model) {
    	Integer dni = (Integer) session.getAttribute("dni");
    	String nroVuelo = (String) session.getAttribute("nroVuelo");

    	Optional<Vuelo> vueloOptional = vueloService.obtenerVueloPorNro(nroVuelo);
    	Cliente c = clienteService.obtenerClientePorDni(dni);
    	 Optional<Impuesto> iva = impuestoService.obtenerImpuestoPorId((long)1);
         Optional<Impuesto> tasa = impuestoService.obtenerImpuestoPorId((long)2);
         
         model.addAttribute("asientos", asientoService.obtenerAsientosLibresPorAvion(vueloOptional.get().getAvion().getId()));
    	model.addAttribute("vueloSeleccionado", vueloOptional);
    	model.addAttribute("cliente", c);
        
        
    	 
    	   if(iva.isPresent() && tasa.isPresent()) {
           	Impuesto ivaVuelo = iva.get();
           	Impuesto tasaVuelo = tasa.get();
           	model.addAttribute("Impuestos", (vueloOptional.get().getPrecioBruto() + tasaVuelo.getMonto()) * (ivaVuelo.getMonto() / 100));
           }
          
        return "venta_pasaje_nacional";
    }
    
    @PostMapping("/venta_pasaje_nacional")
    public String procesarVentaNacional(HttpSession session, Model model) {
        Integer dni = (Integer) session.getAttribute("dni");
        String nroVuelo = (String) session.getAttribute("nroVuelo");

        Cliente c = clienteService.obtenerClientePorDni(dni);
        Optional<Vuelo> vueloOptional = vueloService.obtenerVueloPorNro(nroVuelo);
      
        if (vueloOptional.isPresent()) {
            Vuelo vuelo = vueloOptional.get();
            model.addAttribute("cliente", c);
            model.addAttribute("vuelo", vuelo);

        	List<Asiento> asientosLibres = asientoService.obtenerAsientosLibresPorAvion(vuelo.getAvion().getId());
            model.addAttribute("asientos", asientosLibres);
        	
        }
        return "venta_pasaje_nacional";
    
}
}


