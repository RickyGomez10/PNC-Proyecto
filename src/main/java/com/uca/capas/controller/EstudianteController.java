package com.uca.capas.controller;

import com.uca.capas.domain.*;
import com.uca.capas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class EstudianteController {

    @Autowired
    private CentroEdService centroService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private MateriaXEstudianteService mxeService;

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

    @RequestMapping(value="/materiaCursada")
    public @ResponseBody ModelAndView materiaCursada(@RequestParam String carne) {

        ModelAndView mav = new ModelAndView();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        Estudiante estudiante = estudianteService.findOne(carne);
        MateriaXEstudiante mxe = new MateriaXEstudiante();
        List<Materia> materias = materiaService.findAllActive();

        ArrayList<String> anios = new ArrayList<>();
        for (int i = 2005; i <= c.get(Calendar.YEAR); i++)
            anios.add(""+i);

        mav.addObject("mxe", mxe);
        mav.addObject("materias", materias);
        mav.addObject("estudiante", estudiante);
        mav.addObject("anios", anios);
        mav.addObject("msg", "0");

        mav.setViewName("registroMateria");

        return mav;

    }

    @RequestMapping(value="/regMateriaCursada", method = RequestMethod.POST)
    public @ResponseBody ModelAndView regMateriaCursada(@ModelAttribute("mxe") @Valid MateriaXEstudiante mxe, BindingResult result) {

        ModelAndView mav = new ModelAndView();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        Estudiante estudiante = estudianteService.findById(mxe.getIdEstudiante());

        System.out.println(mxe.toString());

        if(!result.hasErrors()){

            try{
                mxeService.insertar(mxe);
                mav.addObject("msg", "1");
            }catch (ConstraintViolationException e){
                mav.addObject("msg", "3");
            }catch (EntityNotFoundException e){
                mav.addObject("msg", "3");
            }catch (DataIntegrityViolationException e){
                mav.addObject("msg", "2");
            }

            mxe = new MateriaXEstudiante();
            mav.addObject("mxe", mxe);
        }

        List<Materia> materias = materiaService.findAllActive();

        ArrayList<String> anios = new ArrayList<>();
        for (int i = 2005; i <= c.get(Calendar.YEAR); i++)
            anios.add(""+i);

        mav.addObject("materias", materias);
        mav.addObject("estudiante", estudiante);
        mav.addObject("anios", anios);

        mav.setViewName("registroMateria");

        return mav;

    }

    @RequestMapping("/expedientes")
    public ModelAndView expedientes() {

        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", "0");
        mav.setViewName("Expedientes");

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

}
