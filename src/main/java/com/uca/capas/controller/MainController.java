package com.uca.capas.controller;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Municipio;
import com.uca.capas.service.CentroEdService;
import com.uca.capas.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import com.uca.capas.domain.Usuario;
import com.uca.capas.service.UsuarioService;

import java.util.List;

@Controller
public class MainController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MunicipioService municipioService;

	@Autowired
	private CentroEdService centroEdService;
	
	//CARGAR HTMLS
	@RequestMapping("/login")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		Usuario usuario = new Usuario();
		
		mav.addObject("error", "");
		mav.addObject("usuario", usuario);
		mav.setViewName("Login");
		
		return mav;
	}

	@RequestMapping("/centroEd")
	public ModelAndView pantallaIngresarCentroEd() {
		ModelAndView mav = new ModelAndView();
		List<Municipio> municipios = null;
		List<CentroEd> centros = null;
		try{
			municipios = municipioService.findAll();
			centros = centroEdService.findAll();
		}catch(Exception e){
			e.printStackTrace();
		}

		mav.addObject("error", "");
		mav.addObject("municipios", municipios);
		mav.addObject("centros", centros);

		mav.setViewName("IngresarCentro");

		return mav;
	}


	
	//FUNCIONALIDAD

	@RequestMapping(value="/ingresarCentroEd", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody void IngresarCentroEd(@RequestBody CentroEd centro) {

		try {
			centroEdService.save(centro);
			System.out.println("Insertado");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}

	}

	@RequestMapping(value="/cambiarEstadoCentroEd", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody void CambiarCentroEd(@RequestBody CentroEd centro) {

		try {

			CentroEd centro1 = centroEdService.findOne(centro.getIdCentroEd());
			System.out.println(centro1.toString());

			centro1.setEstado(!centro1.getEstado());
			centroEdService.update(centro1);
			System.out.println("Estado cambiado");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		}

	}


	@RequestMapping(value="/verificar", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody boolean verificar(@RequestBody Usuario login) {

		try {
			Usuario user = usuarioService.findUsuarioById(login.getUsuario());


			if(user != null){
				String pass = user.getClave();
				if(pass.equals(login.getClave())){
					user.setSesion(true);

					try {
						usuarioService.save(user);
					}catch (Exception e) {
						e.printStackTrace();
					}

					return true;
				}

				return false;

			}else{
				return false;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
