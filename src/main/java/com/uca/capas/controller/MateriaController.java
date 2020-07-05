package com.uca.capas.controller;

import com.uca.capas.dao.MateriaDAO;
import com.uca.capas.domain.Materia;
import com.uca.capas.service.MateriaService;
import com.uca.capas.utils.CookieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @Autowired
    MateriaDAO materiaDAO;

    @RequestMapping("/ListadoMateria")
    public ModelAndView pantallaListadoMateria(@CookieValue(value = "data", defaultValue = "-") String data) {
        if(CookieData.checkCookie(data)) {
            CookieData cookie = new CookieData(data);
            if (cookie.getRol() == 1) {
                ModelAndView mav = new ModelAndView();
                List<Materia> materias = null;
                try {
                    materias = materiaService.findAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mav.addObject("materias", materias);
                mav.setViewName("ListadoMateria");
                return mav;
            }
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }

    @RequestMapping(value = "/EditarMateria", method = RequestMethod.POST)
    public ModelAndView PantallaEditarCentroEd(@ModelAttribute Materia materia) {
        ModelAndView mav = new ModelAndView();

        System.out.println(materia.getcMateria());
        try {
            materia = materiaService.findOne(materia.getcMateria());
            mav.addObject("materia", materia);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName("EditarMateria");
        return mav;


    }

    @RequestMapping("/IngresarMateria")
    public ModelAndView pantallaIngresarMateria(@CookieValue(value = "data", defaultValue = "-") String data) {

        if(CookieData.checkCookie(data)) {
            CookieData cookie = new CookieData(data);
            if (cookie.getRol() == 1) {
                ModelAndView mav = new ModelAndView();
                Materia materia = new Materia();
                mav.addObject("materia", materia);
                mav.setViewName("RegistrarMateria");
                return mav;
            }
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;

    }

    @RequestMapping(value = "/RegistrarMateria", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String IngresarMateria(@RequestBody Materia materia) {
        String msg;
        if(materia.getNombre().isEmpty() || materia.getNombre() == null || materia.getNombre() == "" ||materia.getNombre().length() >50){
            msg="3";
            return msg;
        }

        try {
            materiaDAO.insertar(materia);;
            msg="1";

        } catch (Exception e) {
            e.printStackTrace();
            msg="2";

        }
        return msg;
    }

    @RequestMapping(value = "/cambiarEstadoMateria", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody boolean CambiarEstadoMateria(@RequestBody Materia materia) {

        try {

            Materia materia1 = materiaService.findOne(materia.getIdMateria());
            materia1.setEstado(!materia1.getEstado());
            materiaService.update(materia1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping(value = "/ModificarMateria", method = RequestMethod.POST)
    public ModelAndView modificar(@Valid @ModelAttribute Materia materia, BindingResult result) {

        ModelAndView mav = new ModelAndView();
        Materia updatear = materiaService.findOne(materia.getIdMateria());
        materia.setEstado(updatear.getEstado());
        if(!result.hasErrors()) {
            try {
                materiaService.insertar(materia);
                mav.setViewName("redirect:/ListadoMateria");
            } catch (Exception e) {
                e.printStackTrace();
                mav.addObject("msg","2");
            }
        }else{
            mav.setViewName("EditarMateria");
            return mav;
        }
        return mav;
    }
}
