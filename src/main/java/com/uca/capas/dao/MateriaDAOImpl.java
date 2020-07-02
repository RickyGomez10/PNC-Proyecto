package com.uca.capas.dao;


import com.uca.capas.domain.Materia;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MateriaDAOImpl implements MateriaDAO {

    @PersistenceContext(unitName = "capas")
    private EntityManager entityManager;

    @Override
    public List<Materia> findAll() throws DataAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from public.materia");
        Query query = entityManager.createNativeQuery(sb.toString(), Materia.class);
        List<Materia> resultset = query.getResultList();
        return resultset;
    }

    @Override
    public Materia findOne(Integer code) throws DataAccessException {
        Materia materia = entityManager.find(Materia.class, code);

        return materia;

    }

    @Override
    @Transactional
    public void insertar(Materia materia) throws DataAccessException {
        entityManager.persist(materia);
    }
}
