package com.uca.capas.controller;

import com.uca.capas.domain.Usuario;
import com.uca.capas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Cargar vistas
    @RequestMapping("/login")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        Usuario usuario = new Usuario();

        mav.addObject("error", "");
        mav.addObject("usuario", usuario);
        mav.setViewName("Login");

        return mav;
    }

    //Controlador
    @RequestMapping(value="/verificar", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean verificar(@RequestBody Usuario login) {

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
