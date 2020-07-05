package com.uca.capas.controller;

import com.uca.capas.domain.*;
import com.uca.capas.service.*;
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
public class EstudianteController {

    @Autowired
    private CentroEdService centroService;

    @Autowired
    private EstudianteService estudianteService;

    @RequestMapping("/registroEstudiante")
    public ModelAndView regForm(@CookieValue(value = "data", defaultValue = "-") String data) {

        ModelAndView mav = new ModelAndView();

        if (CookieData.checkCookie(data)) {
            CookieData cookie = new CookieData(data);
            if (cookie.getRol() == 0) {
                Estudiante estudiante = new Estudiante();
                List<CentroEd> centros = centroService.findAllActive();

                mav.addObject("centros", centros);
                mav.addObject("estudiante", estudiante);
                mav.addObject("msg", "0");
                mav.setViewName("RegistroEstudiante");

                return mav;
            }
        }

        mav.setViewName("redirect:/index");
        return mav;
    }

    @RequestMapping(value="/EditarEstudiante", method = RequestMethod.POST)
    public ModelAndView EditarEstudiante(@ModelAttribute Estudiante estudiante){

        ModelAndView mav = new ModelAndView();

        List<CentroEd> centros = centroService.findAllActive();
        Estudiante e = estudianteService.findOne(estudiante.getCarne());
        mav.addObject("centros", centros);
        mav.addObject("estudiante", e);
        mav.addObject("msg", "0");
        mav.setViewName("EditarEstudiante");

        return mav;

    }

    @RequestMapping(value="/regEst", method = RequestMethod.POST)
    public @ResponseBody ModelAndView verificar(@ModelAttribute @Valid Estudiante estudiante, BindingResult result) {

        ModelAndView mav = new ModelAndView();
        List<CentroEd> centros = centroService.findAllActive();

        if(!result.hasErrors()) {

            try{
                estudianteService.save(estudiante);
                mav.addObject("msg", "1");
                estudiante = new Estudiante();
            }catch (DataIntegrityViolationException e){
                e.printStackTrace();
                if (e.getRootCause().toString().contains("estudiante_carne_key")){
                    mav.addObject("msg", "2");
                }else {
                    mav.addObject("msg", "3");
                }
            } catch (Exception e){
                e.printStackTrace();
                mav.addObject("msg", "3");
            }

            mav.addObject("estudiante", estudiante);

        }

        mav.setViewName("registroEstudiante");
        mav.addObject("centros", centros);
        return mav;

    }

    @RequestMapping("/expedientes")
    public ModelAndView expedientes(@CookieValue(value = "data", defaultValue = "-") String data) {

        ModelAndView mav = new ModelAndView();

        if (CookieData.checkCookie(data)) {
            CookieData cookie = new CookieData(data);
            if (cookie.getRol() == 0) {
                mav.addObject("msg", "0");
                mav.addObject("nombre", cookie.getUsername());
                mav.setViewName("Expedientes");
            } else {
                mav.setViewName("redirect:/");
            }
            return mav;
        }

        mav.setViewName("redirect:/");
        return mav;

    }

    @RequestMapping(value="/buscarEstudiantes", method=RequestMethod.POST)
    public ModelAndView filtrar(@RequestParam(value="crit") String crit, @RequestParam(value="val") String val) {

        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;

        if (crit.equals("1")){

            try {
                estudiantes = estudianteService.filtrarPorNombre(val);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }else if (crit.equals("2")){

            try {
                estudiantes = estudianteService.filtrarPorApellido(val);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            if (crit.equals("0")){
                mav.addObject("msg", "1");
            }else{
                mav.addObject("msg", "2");
            }
            mav.setViewName("Expedientes");
            return mav;
        }

        for ( Estudiante e:estudiantes ) {
            e.setAprobadas(estudianteService.getMateriasAprobadas(e.getIdEstudiante()));
            e.setReprobadas(estudianteService.getMateriasReprobadas(e.getIdEstudiante()));
            e.setPromedio(estudianteService.getPromedio(e.getIdEstudiante()));
        }

        mav.addObject("estudiantes", estudiantes);
        mav.setViewName("ListadoEstudiantes");
        return mav;

    }

    @RequestMapping(value = "/ModificarEstudiante", method = RequestMethod.POST)
    public ModelAndView ModificarEstudiante(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        List<CentroEd> centros = centroService.findAll();
        estudiante.setCentroEd(centroService.findOne(estudiante.getcCentroEd()));
        mav.addObject("centros", centros);
        if(!result.hasErrors()) {
            try {

                estudianteService.update(estudiante);
                mav.setViewName("redirect:/expedientes");

            } catch (Exception e) {
                e.printStackTrace();
                mav.addObject("msg", "1");
                mav.setViewName("EditarEstudiante");
            }
        }else{
            mav.setViewName("EditarEstudiante");
        }
        return mav;
    }

}
