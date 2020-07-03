package com.uca.capas.service;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.repositories.EstudianteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService{

    @Autowired
    EstudianteRepo estudianteRepo;

    @Autowired
    private CentroEdService centroService;

    @Override
    public List<Estudiante> findAll() throws DataAccessException {
        return estudianteRepo.findAll();
    }

    @Override
    public Estudiante findOne(String carne) throws DataAccessException {
        return estudianteRepo.findEstudianteByCarne(carne);
    }

    @Override
    public void save(Estudiante estudiante) throws DataAccessException {
        estudiante.setCentroEd(centroService.findOne(estudiante.getcCentroEd()));
        estudianteRepo.save(estudiante);
    }
}
