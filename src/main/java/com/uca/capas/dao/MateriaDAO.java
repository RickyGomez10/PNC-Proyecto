package com.uca.capas.dao;

import com.uca.capas.domain.Materia;
import com.uca.capas.domain.Municipio;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MateriaDAO {

    public List<Materia> findAll() throws DataAccessException;

    public Materia findOne(Integer code) throws DataAccessException;

    public void insertar(Materia materia) throws DataAccessException;

}
