package com.uca.capas.controller;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.Municipio;
import com.uca.capas.domain.Usuario;
import com.uca.capas.service.CentroEdService;
import com.uca.capas.service.EstudianteService;
import com.uca.capas.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView regForm() {

        ModelAndView mav = new ModelAndView();
        Estudiante estudiante = new Estudiante();
        List<CentroEd> centros = centroService.findAllActive();

        mav.addObject("centros", centros);
        mav.addObject("estudiante", estudiante);
        mav.addObject("msg", "0");
        mav.setViewName("RegistroEstudiante");

        return mav;
    }

    @RequestMapping(value="/regEst", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView verificar(@ModelAttribute @Valid Estudiante estudiante, BindingResult result) {

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

}
