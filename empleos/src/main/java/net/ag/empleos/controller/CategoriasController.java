package net.ag.empleos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.ag.empleos.model.Categoria;
import net.ag.empleos.services.ICategoriaService;

@Controller
@RequestMapping(value = "categorias")
public class CategoriasController {
	@Autowired
	//@Qualifier("categoriasServicejpa") mismo uso que @primary pero debo agragar service en cada implementacion
	 private ICategoriaService serviceCategoria;
	
	

//	@GetMapping("/create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear(Model model) {	
		return "categorias/createCategoria";
	}
	
//	@GetMapping("/salvar")
	@PostMapping(value = "/salvar")
	public String salvar(Categoria categoria,BindingResult result, RedirectAttributes atributes) {	
		if (result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.err.println("Ocurrio un erro tratando de convertir un valor" + error.getDefaultMessage());
			}
			return "categorias/createCategoria";	
		}
		serviceCategoria.salvar(categoria);
		atributes.addFlashAttribute("msj","Registro Salvado con Exito!");	
		System.out.println("Categorias: " + categoria);
		return "redirect:/categorias/listar";
	}
	
	
//	@GetMapping("/listar")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String mostrarCategorias( Model model ) {	
		List<Categoria> lista = serviceCategoria.buscarTCategorias();
		model.addAttribute("categorias", lista);
		return "categorias/listarCategorias";
	}
	
	
	
	

}
