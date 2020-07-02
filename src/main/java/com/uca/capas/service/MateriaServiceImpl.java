package com.uca.capas.service;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Materia;
import com.uca.capas.repositories.CentroEdRepo;
import com.uca.capas.repositories.MateriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService{

    @Autowired
    MateriaRepo materiaRepo;

    @Override
    public List<Materia> findAll() throws DataAccessException {
       return  materiaRepo.findAll();
    }

    @Override
    public Materia findOne(Integer code) throws DataAccessException {
        return materiaRepo.getOne(code);
    }

    @Override
    @Transactional
    public void insertar(Materia materia) throws DataAccessException {
        materiaRepo.save(materia);
    }

    @Override
    @Transactional
    public void update(Materia materia) throws DataAccessException {

        materiaRepo.save(materia);
    }
}
