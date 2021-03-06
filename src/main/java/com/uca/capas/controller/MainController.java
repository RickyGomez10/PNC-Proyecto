package com.uca.capas.controller;

import com.uca.capas.utils.CookieData;
import com.uca.capas.utils.Encriptador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.uca.capas.domain.Usuario;
import com.uca.capas.service.UsuarioService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class MainController {

	@Autowired
	private UsuarioService usuarioService;

	private static final int cookieTime = 3600*24;

	@RequestMapping("/")
	public ModelAndView home(@CookieValue(value = "data", defaultValue = "-") String data) {
		return index(data);
	}

	@RequestMapping("/index")
	public ModelAndView index(@CookieValue(value = "data", defaultValue = "-") String data) {

		if(!CookieData.checkCookie(data)) {
			ModelAndView mav = new ModelAndView();
			Usuario usuario = new Usuario();
			mav.setViewName("index");
			mav.addObject("usuario", usuario);
			return mav;
		}else{
			CookieData cookie = new CookieData(data);
			if (cookie.getRol() == 1){
				ModelAndView mav = new ModelAndView();
				mav.setViewName("redirect:/mainMenu");
				return mav;
			}{
				ModelAndView mav = new ModelAndView();
				mav.setViewName("redirect:/expedientes");
				return mav;
			}

		}
	}

	@RequestMapping(value="/verificar", method = RequestMethod.POST, produces = "application/json")
	public ModelAndView verificar(@ModelAttribute Usuario userInfo, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();

		try {
			Usuario user = usuarioService.findUsuarioById(userInfo.getnUsuario());
			if(user != null){
				String pass = user.getClave();
				if(pass.equals(Encriptador.encriptar(userInfo.getClave()))){
					if (user.getSesion()==false || sessionDateAvailable(user.getFechaSesion())){
						if (user.getEstado()==true){
							user.setSesion(true);

							try {
								CookieData c = new CookieData(user.getnUsuario(), user.getRol());
								Cookie cookie = new Cookie("data", c.toString());
								cookie.setMaxAge(cookieTime);
								response.addCookie(cookie);
								cookie.setHttpOnly(true);
								user.setFechaSesion(new Date());
								usuarioService.sesionUpdate(user);
							}catch (Exception e) {
								e.printStackTrace();
							}
							if (user.getRol() == 1)
								mav.setViewName("redirect:/mainMenu");
							else
								mav.setViewName("redirect:/expedientes");
							return mav;
						}else{
							mav.addObject("error", "El usuario está deshabilitado.");
							mav.setViewName("index");
							return mav;
						}
					}else{
						mav.addObject("error", "El usuario ya tiene una sesión activa.");
						mav.setViewName("index");
						return mav;
					}

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

	private boolean sessionDateAvailable(Date fechaSesion) {

		if(fechaSesion != null) {

			Date now = new Date();
			if (fechaSesion.getTime() + cookieTime * 1000 < now.getTime())
				return true;

			return false;

		}

		return true;

	}

	@RequestMapping("/cerrarSesion")
	public ModelAndView exit(@CookieValue(value = "data", defaultValue = "-") String data, HttpServletResponse response) {

		CookieData cookieData  = new CookieData(data);
		Usuario user = usuarioService.findUsuarioById(cookieData.getUsername());
		user.setSesion(false);
		user.setFechaSesion(null);
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
			if(cookie.getRol() == 1) {
				mav.addObject("nombre", cookie.getUsername());
				mav.setViewName("mainmenu");
			}else{
				mav.setViewName("redirect:/");
			}
			return mav;
		}

		mav.setViewName("redirect:/");
		return mav;
	}

	@GetMapping("/error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error");
		return mav;
	}
}
