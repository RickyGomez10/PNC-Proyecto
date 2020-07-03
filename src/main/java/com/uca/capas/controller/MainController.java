package com.uca.capas.controller;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Municipio;
import com.uca.capas.service.CentroEdService;
import com.uca.capas.service.MunicipioService;
import com.uca.capas.utils.CookieData;
import com.uca.capas.utils.Encriptador;
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
		Usuario usuario = new Usuario();
		mav.setViewName("index");
		mav.addObject("usuario", usuario);
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
	public ModelAndView verificar(@ModelAttribute Usuario userInfo, HttpServletResponse response) {

		System.out.println(userInfo.getnUsuario());
		ModelAndView mav = new ModelAndView();

		try {
			Usuario user = usuarioService.findUsuarioById(userInfo.getnUsuario());
			if(user != null){
				String pass = user.getClave();
				if(pass.equals(Encriptador.encriptar(userInfo.getClave()))){
					user.setSesion(true);

					try {
						CookieData c = new CookieData(user.getnUsuario(), user.getRol());
						System.out.println(c.toString());
						Cookie cookie = new Cookie("data", c.toString());
						cookie.setMaxAge(3600);
						response.addCookie(cookie);
						cookie.setHttpOnly(true);
						usuarioService.sesionUpdate(user);
					}catch (Exception e) {
						e.printStackTrace();
					}
					mav.setViewName("redirect:/mainMenu");
					return mav;
				}
				mav.addObject("error", "Usuario y/o contraseña incorrectos");
				mav.setViewName("index");
				return mav;

			}else{
				mav.addObject("error", "Usuario y/o contraseña incorrectos");
				mav.setViewName("index");
				return mav;
			}

		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("error", e.toString());
			mav.setViewName("index");
			return mav;
		}

	}

	@RequestMapping("/cerrarSesion")
	public ModelAndView exit(@CookieValue(value = "data", defaultValue = "-") String data, HttpServletResponse response) {

		CookieData cookieData  = new CookieData(data);
		Usuario user = usuarioService.findUsuarioById(cookieData.getUsername());
		user.setSesion(false);
		usuarioService.sesionUpdate(user);

		Cookie cookie = new Cookie("data", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		cookie.setHttpOnly(true);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/index");
		return mav;
	}

	@RequestMapping("/mainMenu")
	public ModelAndView menu(@CookieValue(value = "data", defaultValue = "-") String data) {

		ModelAndView mav = new ModelAndView();

		if (CookieData.checkCookie(data)){
			CookieData cookie  = new CookieData(data);
			mav.addObject("nombre", cookie.getUsername());
			mav.setViewName("mainmenu");
			return mav;
		}

		mav.setViewName("redirect:/index");
		return mav;
	}

	@GetMapping("/error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		return mav;
	}
}
