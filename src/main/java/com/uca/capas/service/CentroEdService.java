package com.uca.capas.service;

import com.uca.capas.domain.CentroEd;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CentroEdService {

    public List<CentroEd> findAll() throws DataAccessException;

    public CentroEd findOne(Integer code) throws DataAccessException;

    public void save(CentroEd centroEd) throws DataAccessException;
    public void update(CentroEd centroEd) throws DataAccessException;

    public CentroEd updateEstado(Boolean estado, Integer centroEd) throws DataAccessException;
}
