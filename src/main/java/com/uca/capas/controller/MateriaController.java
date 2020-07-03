package com.uca.capas.controller;

import com.uca.capas.dao.MateriaDAO;
import com.uca.capas.domain.Materia;
import com.uca.capas.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public @ResponseBody boolean IngresarMateria(@RequestBody Materia materia) {

        try {
            materiaDAO.insertar(materia);;
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

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
    public ModelAndView modificar(@ModelAttribute Materia materia) {
        ModelAndView mav = new ModelAndView();
        Materia updatear = materiaService.findOne(materia.getIdMateria());
        updatear.setNombre(materia.getNombre());
        Materia materia1 = new Materia();
        List<Materia> materias = null;

        try {
            materiaService.insertar(updatear);

            materias = materiaService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("error", "");

        mav.addObject("materia", materia1);
        mav.addObject("materias", materias);
        mav.setViewName("ListadoMateria");
        return mav;
    }
}
