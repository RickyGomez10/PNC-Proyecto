package com.uca.capas.controller;

import com.uca.capas.dao.UsuarioDAO;
import com.uca.capas.domain.Municipio;
import com.uca.capas.domain.Usuario;
import com.uca.capas.service.MunicipioService;
import com.uca.capas.service.UsuarioService;
import com.uca.capas.utils.CookieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MunicipioService muniService;

    @Autowired
    private UsuarioDAO usuarioDAO;
    //CARGAR VISTAS

    @RequestMapping("/registro")
    public ModelAndView regForm(@CookieValue(value = "data", defaultValue = "-") String data) {

        if(!CookieData.checkCookie(data)) {

            ModelAndView mav = new ModelAndView();
            Usuario usuario = new Usuario();
            List<Municipio> municipios = muniService.findAll();

            mav.addObject("municipios", municipios);
            mav.addObject("usuario", usuario);
            mav.addObject("msg", "0");
            mav.setViewName("Registro");
            return mav;

        }else{
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/");
            return mav;
        }

    }

    @RequestMapping("/listadoUsuarios")
    public ModelAndView listadoUsuariosForm(@CookieValue(value = "data", defaultValue = "-") String data) {

        if(CookieData.checkCookie(data)) {
            CookieData cookie = new CookieData(data);
            if (cookie.getRol() == 1) {
                ModelAndView mav = new ModelAndView();
                Usuario usuario = new Usuario();
                List<Usuario> usuarios = usuarioService.findAll();
                mav.addObject("usuarios", usuarios);
                mav.addObject("usuario", usuario);
                mav.setViewName("ListadoUsuario");
                return mav;
            }
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;

    }

    @RequestMapping(value = "/EditarUsuario", method = RequestMethod.POST)
    public ModelAndView editarForm(@ModelAttribute Usuario usuario) {

        ModelAndView mav = new ModelAndView();
        Usuario usuario1 = new Usuario();
        List<Municipio> municipios = muniService.findAll();
        usuario1 = usuarioDAO.findOne(usuario.getIdUsuario());
        mav.addObject("municipios", municipios);
        mav.addObject("usuario", usuario1);
        mav.addObject("msg", "0");
        mav.setViewName("EditarUsuario");

        return mav;
    }

    @RequestMapping(value="/regUser", method = RequestMethod.POST)
    public @ResponseBody ModelAndView verificar(@ModelAttribute @Valid Usuario usuario, BindingResult result) {

        ModelAndView mav = new ModelAndView();
        List<Municipio> municipios = muniService.findAll();

        if(!result.hasErrors()) {

            try{
                usuarioService.save(usuario);
                mav.addObject("msg", "1");
                usuario = new Usuario();
            }catch (DataIntegrityViolationException e){
                e.printStackTrace();
                if (e.getRootCause().toString().contains("usuario_usuario_key")){
                    mav.addObject("msg", "2");
                }else {
                    mav.addObject("msg", "3");
                }
            } catch (Exception e){
                e.printStackTrace();
                mav.addObject("msg", "3");
            }

            mav.addObject("usuario", usuario);

        }

        mav.setViewName("registro");
        mav.addObject("municipios", municipios);
        return mav;

    }

    @RequestMapping(value = "/cambiarEstadoUsuario", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody boolean CambiarEstadoUsuario(@RequestBody Usuario usuario) {

        try {

            Usuario usuario1 = usuarioDAO.findOne(usuario.getIdUsuario());
            System.out.println(usuario1.toString());
            usuario1.setEstado(!usuario1.getEstado());
            usuarioDAO.save(usuario1);
            System.out.println("Estado cambiado");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
            return false;
        }

    }

    @RequestMapping(value="/ModificarUsuario", method = RequestMethod.POST)
    public @ResponseBody ModelAndView ModificarUsuario(@ModelAttribute @Valid Usuario usuario, BindingResult result) {

        ModelAndView mav = new ModelAndView();
        List<Municipio> municipios = muniService.findAll();
        mav.addObject("municipios", municipios);
        if(!result.hasErrors()) {
            try{
                usuarioService.update(usuario);
                mav.setViewName("redirect:/listadoUsuarios");
            } catch (Exception e){
                e.printStackTrace();
                mav.addObject("msg", "1");
                mav.setViewName("EditarUsuario");
            }

        }else{
            mav.setViewName("EditarUsuario");
        }
        return mav;

    }

}
