package com.uca.capas.controller;

import com.uca.capas.dao.MateriaDAO;
import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Materia;
import com.uca.capas.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MateriaController {

    @Autowired
    MateriaService materiaService;

    @Autowired
    MateriaDAO materiaDAO;

    //Cargar vistas
    @RequestMapping("/materia")
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
        mav.setViewName("IngresarMateria");
        return mav;
    }

    //Controladores

    @RequestMapping(value = "/ingresarMateria", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody boolean IngresarMateria(@RequestBody Materia materia) {

        try {
            materiaDAO.insertar(materia);;
            System.out.println("Insertado");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
            return false;
        }

    }

    @RequestMapping(value = "/cambiarEstadoMateria", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody boolean CambiarCentroEd(@RequestBody Materia materia) {

        try {

            Materia materia1 = materiaService.findOne(materia.getIdMateria());
            System.out.println(materia1.toString());

            materia1.setEstado(!materia1.getEstado());
            materiaService.update(materia1);
            System.out.println("Estado cambiado");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
            return false;
        }

    }
}
