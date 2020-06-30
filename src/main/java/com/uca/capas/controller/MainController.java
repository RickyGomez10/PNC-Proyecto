package com.uca.capas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import com.uca.capas.domain.Usuario;
import com.uca.capas.service.UsuarioService;

@Controller
public class MainController {

	@Autowired
	private UsuarioService usuarioService;
	

	@RequestMapping("/login")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		Usuario usuario = new Usuario();
		
		mav.addObject("error", "");
		mav.addObject("usuario", usuario);
		mav.setViewName("Login");
		
		return mav;
	}
	
	
	@RequestMapping(value="/verificar", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody boolean verificar(@RequestBody Usuario login) {

		try {
			String pass = usuarioService.findPasswordById(login.getUsuario());

			if(pass != null){
				if(pass.equals(login.getClave())){
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
