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
    @RequestMapping("/ListadoCentroEd")
    public ModelAndView listadoCentroEd() {
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
        mav.setViewName("ListadoCentro");

        return mav;
    }
    @RequestMapping("/IngresarUsuario")
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
        mav.setViewName("RegistrarCentro");

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
    public @ResponseBody boolean IngresarCentroEd(@RequestBody CentroEd centro) {

        try {
            centroEdService.save(centro);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @RequestMapping(value = "/cambiarEstadoCentroEd", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    boolean CambiarCentroEd(@RequestBody CentroEd centro) {

        try {

            CentroEd centro1 = centroEdService.findOne(centro.getIdCentroEd());


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
        System.out.println(centroEd.getcMunicipio());
        CentroEd updatear = centroEdService.findOne(centroEd.getIdCentroEd());
        List<Municipio> municipios = null;
        List<CentroEd> centros = null;
        CentroEd centro = new CentroEd();
        updatear.setcMunicipio(centroEd.getcMunicipio());
        updatear.setNombre(centroEd.getNombre());

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
        mav.setViewName("ListadoCentro");
        return mav;
    }
}

