package com.uca.capas.service;

import com.uca.capas.dao.CentroEdDAO;
import com.uca.capas.domain.CentroEd;
import com.uca.capas.repositories.CentroEdRepo;
import com.uca.capas.repositories.MunicipioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class CentroEdServiceImpl implements CentroEdService {
    @Autowired
    CentroEdRepo centroRepo;

    @Autowired
    MunicipioService municipioService;

    @Autowired
    CentroEdService centroEdService;

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
        centroEd.setMunicipio(municipioService.findOne(centroEd.getcMunicipio()));
        centroRepo.save(centroEd);
    }

    @Override
    @Transactional
    public void update(CentroEd centroEd) throws DataAccessException {

        centroRepo.save(centroEd);
    }

    @Override
    public CentroEd updateEstado(Boolean estado,Integer centroEd) throws DataAccessException {

        return centroRepo.updateEstado(estado, centroEd);
    }


}
