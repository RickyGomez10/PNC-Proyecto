package com.uca.capas.dao;

import com.uca.capas.domain.CentroEd;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface CentroEdDAO {

    public List<CentroEd> findAll() throws DataAccessException;

    public CentroEd findOne(Integer code) throws DataAccessException;

    public void insertar(CentroEd centroEd) throws DataAccessException;
}
