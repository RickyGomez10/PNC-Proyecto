package com.uca.capas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		
		mav.addObject("usuario", usuario);
		mav.setViewName("Login");
		
		return mav;
	}
	
	
	@RequestMapping("/verificar")
	public @ResponseBody Usuario verificar(@RequestParam String user, @RequestParam String clave) {
		
		try {
			Usuario usuario = usuarioService.findUsuarioById(user);
			
			if(usuario.getUsuario().equals(user)) {
				System.out.println("Si");	
				if(usuario.getClave().equals(clave)) {
				System.out.println("Si");	
				return usuario;
				}else {
					System.out.println("no contra");	
				}
			}else {
				System.out.println("no usuario");	
				return usuario;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
			System.out.println("error");	
			
		}
		return null;
		
		
		
		
	
	}
	
}
