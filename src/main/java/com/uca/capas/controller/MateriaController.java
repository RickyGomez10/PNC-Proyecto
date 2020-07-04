package com.uca.capas.controller;

import com.uca.capas.dao.MateriaDAO;
import com.uca.capas.domain.Materia;
import com.uca.capas.service.MateriaService;
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

    //Cargar vistas
    @RequestMapping("/ListadoMateria")
    public ModelAndView pantallaListadoMateria() {
        ModelAndView mav = new ModelAndView();
        Materia materia = new Materia();
        List<Materia> materias = null;
        try {
            materias = materiaService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("error", "");

        mav.addObject("materias", materias);
        mav.addObject("materia", materia);
        mav.setViewName("ListadoMateria");
        return mav;
    }

    @RequestMapping("/EditarMateria")
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
    public ModelAndView pantallaIngresarMateria() {
        ModelAndView mav = new ModelAndView();
        Materia materia = new Materia();
        List<Materia> materias = null;
        try {
            materias = materiaService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("error", "");

        mav.addObject("materias", materias);
        mav.addObject("materia", materia);
        mav.setViewName("RegistrarMateria");
        return mav;
    }

    //Controladores

    @RequestMapping(value = "/RegistrarMateria", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String IngresarMateria(@RequestBody Materia materia) {
        String msg;
        if(materia.getNombre().isEmpty() || materia.getNombre() == null || materia.getNombre() == "" ||materia.getNombre().length() >50){
            msg="2";
            return msg;
        }

        try {
            materiaDAO.insertar(materia);;
            msg="1";

        } catch (Exception e) {
            e.printStackTrace();
            msg="3";

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
        List<Materia> materias = materiaService.findAll();
        mav.addObject("materias", materias);

        Materia updatear = materiaService.findOne(materia.getIdMateria());
        materia.setEstado(updatear.getEstado());
        Materia materia1 = new Materia();

        if(!result.hasErrors()) {
            try {
                materiaService.insertar(materia);
                mav.setViewName("ListadoMateria");
                mav.addObject("msg", "1");
            } catch (Exception e) {
                e.printStackTrace();
                mav.addObject("msg","2");
            }
        }else{
            mav.setViewName("EditarMateria");
            return mav;
        }

        mav.addObject("error", "");

        mav.addObject("materia", materia1);


        return mav;
    }
}
