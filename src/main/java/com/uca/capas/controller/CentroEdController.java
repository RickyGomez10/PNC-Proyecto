package com.uca.capas.controller;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Municipio;
import com.uca.capas.service.CentroEdService;
import com.uca.capas.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CentroEdController {

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private CentroEdService centroEdService;

    //Cargar vistas
    @RequestMapping("/centroEd")
    public ModelAndView pantallaIngresarCentroEd() {
        ModelAndView mav = new ModelAndView();
        CentroEd centro = new CentroEd();
        List<Municipio> municipios = null;
        List<CentroEd> centros = null;
        try {
            municipios = municipioService.findAll();
            centros = centroEdService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("error", "");
        mav.addObject("municipios", municipios);
        mav.addObject("centros", centros);
        mav.addObject("centroEd", centro);
        mav.setViewName("IngresarCentro");

        return mav;
    }

    @RequestMapping("/EditarForm")
    public ModelAndView PantallaEditarCentroEd(@ModelAttribute CentroEd centroEd) {
        ModelAndView mav = new ModelAndView();
        List<Municipio> municipios = municipioService.findAll();
        System.out.println(centroEd.getcMunicipio());
        try {
            centroEd = centroEdService.findOne(centroEd.getcMunicipio());
            mav.addObject("centroEd", centroEd);

        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("municipios", municipios);
        mav.setViewName("EditarCentro");
        return mav;


    }


    //Controladores
    @RequestMapping(value = "/ingresarCentroEd", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    void IngresarCentroEd(@RequestBody CentroEd centro) {

        try {
            centroEdService.save(centro);
            System.out.println("Insertado");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }

    }

    @RequestMapping(value = "/cambiarEstadoCentroEd", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean CambiarCentroEd(@RequestBody CentroEd centro) {

        try {

            CentroEd centro1 = centroEdService.findOne(centro.getIdCentroEd());
            System.out.println(centro1.toString());

            centro1.setEstado(!centro1.getEstado());
            centroEdService.update(centro1);
            System.out.println("Estado cambiado");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
            return false;
        }

    }

    @RequestMapping(value = "/Modificar", method = RequestMethod.POST)
    public ModelAndView modificar(@ModelAttribute CentroEd centroEd) {
        ModelAndView mav = new ModelAndView();
        CentroEd updatear = centroEdService.findOne(centroEd.getIdCentroEd());
        List<Municipio> municipios = null;
        List<CentroEd> centros = null;
        CentroEd centro = new CentroEd();
        updatear.setcMunicipio(centroEd.getcMunicipio());

        try {
            centroEdService.save(updatear);
            municipios = municipioService.findAll();
            centros = centroEdService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("error", "");
        mav.addObject("municipios", municipios);
        mav.addObject("centros", centros);
        mav.addObject("centroEd", centro);
        mav.setViewName("IngresarCentro");
        return mav;
    }
}

