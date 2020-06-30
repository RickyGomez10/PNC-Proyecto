package com.uca.capas.service;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Estudiante;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface EstudianteService {

    public List<Estudiante> findAll() throws DataAccessException;

    public Estudiante findOne(String code) throws DataAccessException;

    public void save(Estudiante estudiante) throws DataAccessException;
}
