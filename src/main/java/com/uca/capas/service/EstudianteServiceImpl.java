package com.uca.capas.service;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.repositories.EstudianteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

public class EstudianteServiceImpl implements EstudianteService{
    @Autowired
    EstudianteRepo estudianteRepo;


    @Override
    public List<Estudiante> findAll() throws DataAccessException {
        return estudianteRepo.findAll();
    }

    @Override
    public Estudiante findOne(String codigo) throws DataAccessException {
        return estudianteRepo.getOne(codigo);
    }

    @Override
    public void save(Estudiante estudiante) throws DataAccessException {
            estudianteRepo.save(estudiante);
    }
}
