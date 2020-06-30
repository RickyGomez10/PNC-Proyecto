package com.uca.capas.service;

import com.uca.capas.domain.Materia;
import com.uca.capas.domain.Municipio;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MunicipioService {

    public List<Municipio> findAll() throws DataAccessException;

    public Municipio findOne(Integer code) throws DataAccessException;

    public void insertar(Municipio municipio) throws DataAccessException;


}
