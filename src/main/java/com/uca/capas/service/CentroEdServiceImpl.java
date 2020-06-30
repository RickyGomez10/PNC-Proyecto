package com.uca.capas.service;

import com.uca.capas.dao.CentroEdDAO;
import com.uca.capas.domain.CentroEd;
import com.uca.capas.repositories.CentroEdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class CentroEdServiceImpl implements CentroEdService {
    @Autowired
    CentroEdRepo centroRepo;


    @Override
    public List<CentroEd> findAll() throws DataAccessException {
        return centroRepo.findAll();
    }

    @Override
    public CentroEd findOne(Integer code) throws DataAccessException {
        return centroRepo.getOne(code);
    }

    @Override
    @Transactional
    public void save(CentroEd centroEd) throws DataAccessException {
        centroRepo.save(centroEd);

    }
}
