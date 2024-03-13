package com.app.AeropuertoSauceViejo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.AeropuertoSauceViejo.Entitys.Asiento;
import com.app.AeropuertoSauceViejo.Entitys.Cliente;
import com.app.AeropuertoSauceViejo.Entitys.Impuesto;
import com.app.AeropuertoSauceViejo.Entitys.Pasaje;
import com.app.AeropuertoSauceViejo.Entitys.Vuelo;
import com.app.AeropuertoSauceViejo.Exceptions.CustomException;
import com.app.AeropuertoSauceViejo.Services.AsientoService;
import com.app.AeropuertoSauceViejo.Services.ClienteService;
import com.app.AeropuertoSauceViejo.Services.ImpuestoService;
import com.app.AeropuertoSauceViejo.Services.PasajeService;
import com.app.AeropuertoSauceViejo.Services.VueloService;

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

		return "redirect:/venta_pasaje";

	}

	@GetMapping("/venta_pasaje")
	public String MostrarventaPasajes(HttpSession session, Model model) {
		Integer dni = (Integer) session.getAttribute("dni");
		String nroVuelo = (String) session.getAttribute("nroVuelo");

		Optional<Vuelo> vueloOptional = vueloService.obtenerVueloPorNro(nroVuelo);
		Cliente c = clienteService.obtenerClientePorDni(dni);

		model.addAttribute("asientos", asientoService.obtenerAsientosLibresPorVuelo(vueloOptional.get().getNroVuelo()));
		model.addAttribute("vueloSeleccionado", vueloOptional);
		model.addAttribute("cliente", c);

		Double iva = 0.0;
		Double tasaAepNacional = 0.0;
		Double tasaAepInternacional = 0.0;
		Double cotizacionDolar = 0.0;

		List<Impuesto> impuestos = impuestoService.obtenerImpuestos();

		for (Impuesto i : impuestos) {
			if (i.getNombre().equalsIgnoreCase("IVA")) {
				iva = i.getMonto();
			}
			if (i.getNombre().equalsIgnoreCase("TASA AEROPUERTARIA NACIONAL")) {
				tasaAepNacional = i.getMonto();
			}
			if (i.getNombre().equalsIgnoreCase("TASA AEROPUERTARIA INTERNACIONAL")) {
				tasaAepInternacional = i.getMonto();
			}
			if(i.getNombre().equalsIgnoreCase("COTIZACION DOLAR")){
				cotizacionDolar = i.getMonto();
			}
		}

		if (vueloOptional.get().getTipoVuelo().equalsIgnoreCase("Nacional")) {

			model.addAttribute("Impuestos", (vueloOptional.get().getPrecioBruto()
					+ (vueloOptional.get().getPrecioBruto() + tasaAepNacional * (iva / 100))));

		} else {

			model.addAttribute("Impuestos",
					(vueloOptional.get().getPrecioBruto() * cotizacionDolar + tasaAepInternacional));
		}

		return "/venta_pasaje";
	}

	@PostMapping("/venta_pasaje")
	public String procesarVenta(@RequestParam(name = "asiento") Long id, HttpSession session, Model model) throws CustomException {
		Integer dni = (Integer) session.getAttribute("dni");
		String nroVuelo = (String) session.getAttribute("nroVuelo");

		Cliente c = clienteService.obtenerClientePorDni(dni);
		Optional<Vuelo> vueloOptional = vueloService.obtenerVueloPorNro(nroVuelo);

		if (vueloOptional.isPresent()) {
			Vuelo vuelo = vueloOptional.get();
			model.addAttribute("cliente", c);
			model.addAttribute("vuelo", vuelo);

			List<Asiento> asientosLibres = asientoService.obtenerAsientosLibresPorVuelo(vuelo.getNroVuelo());
			model.addAttribute("asientos", asientosLibres);

		}

		Pasaje pasaje = new Pasaje();
		pasaje.setAsiento(asientoService.findById(id).get());
		Asiento asientoSelect = asientoService.findById(id).get();
		asientoSelect.setId(id);

		
		pasaje.setCliente(c);

		pasaje.setVuelo(vueloOptional.get());

		Double iva = 0.0;
		Double tasaAepNacional = 0.0;
		Double tasaAepInternacional = 0.0;
		Double cotizacionDolar = 0.0;

		List<Impuesto> impuestos = impuestoService.obtenerImpuestos();

		for (Impuesto i : impuestos) {
			if (i.getNombre().equalsIgnoreCase("IVA")) {
				iva = i.getMonto();
			}
			if (i.getNombre().equalsIgnoreCase("TASA AEROPUERTARIA NACIONAL")) {
				tasaAepNacional = i.getMonto();
			}
			if (i.getNombre().equalsIgnoreCase("TASA AEROPUERTARIA INTERNACIONAL")) {
				tasaAepInternacional = i.getMonto();
			}
			if(i.getNombre().equalsIgnoreCase("COTIZACION DOLAR")){
				cotizacionDolar = i.getMonto();
			}
		}

		if (vueloOptional.get().getTipoVuelo().equalsIgnoreCase("Nacional")) {

			pasaje.setPrecio((vueloOptional.get().getPrecioBruto() + (vueloOptional.get().getPrecioBruto() + tasaAepNacional * (iva / 100))));
		} else {
			pasaje.setPrecio((vueloOptional.get().getPrecioBruto() * cotizacionDolar + tasaAepInternacional));
		}
		
		pasaje.setFechaHora(vueloOptional.get().getFechaHora());
		
		//ERROR EN EL TP ENTREGADO, GUARDA EL PASAJE ANTES DE TIRAR EL ERROR DE NO TENER PASAPORTE Y SE TERMINABA GUARDANDO IGUAL.
		//pasajeService.crearPasaje(pasaje);

		session.setAttribute("id", pasaje.getId());

		if (vueloService.obtenerVueloPorNro(nroVuelo).get().getTipoVuelo().equalsIgnoreCase("INTERNACIONAL")) {
			if (c.getNroPasaporte() == null) {
				throw new CustomException("No puede comprar un pasaje Internacional sin tener Pasaporte.");
			}
			
			pasajeService.crearPasaje(pasaje);
			
			asientoSelect.setReservado(true);
			asientoService.actualizarAsiento(asientoSelect);

		}

		return "redirect:/finalizar_venta";

	}

	@GetMapping("/finalizar_venta")
	public String finalizarVenta(HttpSession session, Model model) throws CustomException {
		Long id = (Long) session.getAttribute("id");

		if (!pasajeService.findById(id).isPresent()) {
			throw new CustomException("No se pudo vender el pasaje");
		}

		String mensajeExito = "¡Pasaje emitido con éxito!";

		model.addAttribute("mensajeExito", mensajeExito);

		return "finalizar_venta";

	}
}