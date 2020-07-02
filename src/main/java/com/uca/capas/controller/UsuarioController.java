package com.uca.capas.controller;

import com.uca.capas.domain.Municipio;
import com.uca.capas.domain.Usuario;
import com.uca.capas.service.MunicipioService;
import com.uca.capas.service.UsuarioService;
import com.uca.capas.utils.CookieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MunicipioService muniService;

    @RequestMapping("/registro")
    public ModelAndView regForm() {

        ModelAndView mav = new ModelAndView();
        Usuario usuario = new Usuario();
        List<Municipio> municipios = muniService.findAll();

        mav.addObject("municipios", municipios);
        mav.addObject("usuario", usuario);
        mav.setViewName("Registro");

        return mav;
    }

    @RequestMapping(value="/regUser", method = RequestMethod.POST)
    public @ResponseBody ModelAndView verificar(@ModelAttribute @Valid Usuario usuario, BindingResult result) {

        ModelAndView mav = new ModelAndView();
        List<Municipio> municipios = muniService.findAll();

        if(!result.hasErrors()) {

            try{
                usuarioService.save(usuario);
            }catch (Exception e){
                e.printStackTrace();
            }

            usuario = new Usuario();
            mav.addObject("usuario", usuario);

        }

        mav.setViewName("registro");
        mav.addObject("municipios", municipios);
        return mav;

    }

}
