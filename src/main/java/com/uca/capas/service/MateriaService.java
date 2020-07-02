package com.uca.capas.service;

import com.uca.capas.domain.Materia;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MateriaService {

    public List<Materia> findAll() throws DataAccessException;

    public Materia findOne(Integer code) throws DataAccessException;

    public void insertar(Materia materia) throws DataAccessException;

    public void update(Materia materia) throws DataAccessException;

}
