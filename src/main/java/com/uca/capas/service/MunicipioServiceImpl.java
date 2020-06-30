package com.uca.capas.service;

import com.uca.capas.domain.Municipio;
import com.uca.capas.repositories.MunicipioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioServiceImpl implements MunicipioService{
    @Autowired
    MunicipioRepo municipioRepo;

    @Override
    public List<Municipio> findAll() throws DataAccessException {
        return municipioRepo.findAll();
    }

    @Override
    public Municipio findOne(Integer code) throws DataAccessException {
        return municipioRepo.getOne(code);
    }

    @Override
    public void insertar(Municipio municipio) throws DataAccessException {
        municipioRepo.save(municipio);
    }
}
