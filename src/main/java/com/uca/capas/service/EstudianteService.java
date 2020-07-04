package com.uca.capas.service;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Estudiante;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface EstudianteService {

    public List<Estudiante> findAll() throws DataAccessException;

    public Estudiante findOne(String carne) throws DataAccessException;

    public Estudiante findById(Integer id) throws DataAccessException;

    public void save(Estudiante estudiante) throws DataAccessException;

    public List<Estudiante> filtrarPorNombre(String val);

    public List<Estudiante> filtrarPorApellido(String val);

    public Integer getMateriasAprobadas(Integer id) throws DataAccessException;

    public Integer getMateriasReprobadas(Integer id) throws DataAccessException;

    public Float getPromedio(Integer id) throws DataAccessException;

}
