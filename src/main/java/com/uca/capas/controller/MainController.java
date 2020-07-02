package com.uca.capas.controller;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Municipio;
import com.uca.capas.service.CentroEdService;
import com.uca.capas.service.MunicipioService;
import com.uca.capas.utils.CookieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import com.uca.capas.domain.Usuario;
import com.uca.capas.service.UsuarioService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MunicipioService municipioService;

	@Autowired
	private CentroEdService centroEdService;

	@RequestMapping("/index")
	public ModelAndView index(@CookieValue(value = "data", defaultValue = "-") String data) {

		System.out.println(data);
		ModelAndView mav = new ModelAndView();

		mav.setViewName("index");

		return mav;
	}
	
	//CARGAR HTMLS
	@RequestMapping("/login")
	public ModelAndView login(@CookieValue(value = "data", defaultValue = "-") String data) {

		System.out.println(data);
		ModelAndView mav = new ModelAndView();
		Usuario usuario = new Usuario();
		
		mav.addObject("error", "");
		mav.addObject("usuario", usuario);
		mav.setViewName("Login");
		
		return mav;
	}



	
	//FUNCIONALIDAD



	@RequestMapping(value="/verificar", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody boolean verificar(@RequestBody Usuario login, HttpServletResponse response) {

		System.out.println(response.toString());

		try {
			Usuario user = usuarioService.findUsuarioById(login.getUsuario());

			if(user != null){
				String pass = user.getClave();
				if(pass.equals(login.getClave())){
					user.setSesion(true);

					try {
						CookieData c = new CookieData(user.getUsuario(), user.getRol());
						System.out.println(c.toString());
						Cookie cookie = new Cookie("data", c.toString());
						cookie.setMaxAge(3600);
						response.addCookie(cookie);
						cookie.setHttpOnly(true);
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

	@GetMapping("/Error.html")
	public void readCookie(@CookieValue(value = "data", defaultValue = "Atta") String username) {
		System.out.println(username);
	}
}
