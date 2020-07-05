package com.uca.capas.controller;

import com.uca.capas.domain.*;
import com.uca.capas.service.CentroEdService;
import com.uca.capas.service.EstudianteService;
import com.uca.capas.service.MateriaService;
import com.uca.capas.service.MateriaXEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class MateriaXEstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private MateriaXEstudianteService mxeService;

    @RequestMapping(value="/materiaCursada", method = RequestMethod.POST)
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

    @RequestMapping(value="/editarMateriaCursada", method = RequestMethod.POST)
    public @ResponseBody ModelAndView editarMateriaCursada(@RequestParam String carne, @RequestParam Integer mat) {

        ModelAndView mav = new ModelAndView();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        Estudiante estudiante = estudianteService.findOne(carne);
        MateriaXEstudianteKey id = new MateriaXEstudianteKey(mat, estudiante.getIdEstudiante());
        MateriaXEstudiante mxe = mxeService.findOne(id);

        ArrayList<String> anios = new ArrayList<>();
        for (int i = 2005; i <= c.get(Calendar.YEAR); i++)
            anios.add(""+i);

        mav.addObject("mxe", mxe);
        mav.addObject("estudiante", estudiante);
        mav.addObject("anios", anios);
        mav.addObject("msg", "0");

        mav.setViewName("editarMateriaCursada");

        return mav;

    }

    @RequestMapping(value="/edMateriaCursada", method = RequestMethod.POST)
    public @ResponseBody ModelAndView edMateriaCursada(@ModelAttribute("mxe") @Valid MateriaXEstudiante mxe, BindingResult result) {

        ModelAndView mav = new ModelAndView();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        Estudiante estudiante = estudianteService.findById(mxe.getIdEstudiante());

        System.out.println(mxe.toString());

        if(!result.hasErrors()){

            try{
                mxeService.modificar(mxe);
                return listadoMatCurs(estudiante.getCarne());
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

    @RequestMapping(value="/materiasCursadas", method = RequestMethod.POST)
    public @ResponseBody ModelAndView listadoMatCurs(@RequestParam String carne) {

        ModelAndView mav = new ModelAndView();

        Estudiante estudiante = estudianteService.findOne(carne);
        List<MateriaXEstudiante> materias = mxeService.findByEstudiante(estudiante.getIdEstudiante());

        mav.addObject("materias", materias);
        mav.addObject("estudiante", estudiante);

        mav.setViewName("materiasCursadas");

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

}
