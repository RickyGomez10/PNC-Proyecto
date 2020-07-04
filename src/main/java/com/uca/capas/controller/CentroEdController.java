package com.uca.capas.controller;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Municipio;
import com.uca.capas.service.CentroEdService;
import com.uca.capas.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    @RequestMapping("/IngresarCentro")
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
        boolean estado;
        try {
            centroEdService.save(centro);
            estado = true;
        } catch (Exception e) {
            System.out.println("malo");
            estado = false;
        }
                return estado;
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
    public ModelAndView modificar(@Valid @ModelAttribute CentroEd centroEd, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        List<Municipio> municipios = municipioService.findAll();
        List<CentroEd> centros = centroEdService.findAll();
        mav.addObject("municipios", municipios);
        CentroEd updatear = centroEdService.findOne(centroEd.getIdCentroEd());
        centroEd.setMunicipio(municipioService.findOne(centroEd.getcMunicipio()));
        centroEd.setEstado(updatear.getEstado());

        if(result.hasErrors()){
            //mav.addObject("centroEd", centroEd);
            mav.setViewName("EditarCentro");
            return mav;
        }
        CentroEd centro = new CentroEd();
        try {

            centroEdService.save(centroEd);
            mav.setViewName("ListadoCentro");
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("error", "");

        mav.addObject("centros", centros);
        mav.addObject("centroEd", centro);

        return mav;
    }
}

