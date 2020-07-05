package com.uca.capas.controller;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Municipio;
import com.uca.capas.service.CentroEdService;
import com.uca.capas.service.MunicipioService;
import com.uca.capas.utils.CookieData;
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

    @RequestMapping("/ListadoCentroEd")
    public ModelAndView listadoCentroEd(@CookieValue(value = "data", defaultValue = "-") String data) {

        if(CookieData.checkCookie(data)) {
            CookieData cookie = new CookieData(data);
            if (cookie.getRol() == 1) {
                ModelAndView mav = new ModelAndView();
                List<CentroEd> centros = null;
                try {
                    centros = centroEdService.findAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mav.addObject("msg", "");
                mav.addObject("centros", centros);
                mav.setViewName("ListadoCentro");
                return mav;
            }
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;

    }

    @RequestMapping("/IngresarCentro")
    public ModelAndView pantallaIngresarCentroEd(@CookieValue(value = "data", defaultValue = "-") String data) {

        if(CookieData.checkCookie(data)) {
            CookieData cookie = new CookieData(data);
            if (cookie.getRol() == 1) {
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

                mav.addObject("municipios", municipios);
                mav.addObject("centros", centros);
                mav.addObject("centroEd", centro);
                mav.setViewName("RegistrarCentro");

                return mav;
            }
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;

    }

    @RequestMapping(value = "/EditarForm", method = RequestMethod.POST)
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

    @RequestMapping(value = "/ingresarCentroEd", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody String IngresarCentroEd(@RequestBody CentroEd centro) {
        String msg;
        if(centro.getNombre().length() < 1 || centro.getNombre().length()>50){
            msg = "3";

        }else {
            try {
                centroEdService.save(centro);
                msg = "1";
            } catch (Exception e) {
                msg="2";

            }
        }
                return msg;
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

    @RequestMapping(value = "/ModificarCentro", method = RequestMethod.POST)
    public ModelAndView ModificarCentro(@Valid @ModelAttribute CentroEd centroEd, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        List<Municipio> municipios = municipioService.findAll();
        List<CentroEd> centros = centroEdService.findAll();
        mav.addObject("municipios", municipios);
        CentroEd updatear = centroEdService.findOne(centroEd.getIdCentroEd());
        centroEd.setMunicipio(municipioService.findOne(centroEd.getcMunicipio()));
        centroEd.setEstado(updatear.getEstado());

        if(!result.hasErrors()) {
            try {

                centroEdService.save(centroEd);
                mav.setViewName("redirect:/ListadoCentroEd");
            } catch (Exception e) {
                e.printStackTrace();
                mav.addObject("msg","1");
                mav.setViewName("EditarCentro");
            }
        }else{

            mav.setViewName("EditarCentro");
            return mav;
        }

        return mav;
    }
}

