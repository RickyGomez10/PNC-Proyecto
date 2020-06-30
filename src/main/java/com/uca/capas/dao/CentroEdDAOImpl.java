package com.uca.capas.dao;

import com.uca.capas.domain.CentroEd;

import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class CentroEdDAOImpl implements CentroEdDAO{

    @PersistenceContext(unitName="capas")
    private EntityManager entityManager;

    @Override
    public List<CentroEd> findAll() throws DataAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from public.centro_ed");
        Query query = entityManager.createNativeQuery(sb.toString(), CentroEd.class);
        List<CentroEd> resultset = query.getResultList();
        return resultset;
    }

    @Override
    public CentroEd findOne(Integer code) throws DataAccessException {
            CentroEd centro = entityManager.find(CentroEd.class, code);

            return centro;

    }

    @Override
    @Transactional
    public void insertar(CentroEd centroEd) throws DataAccessException {
        entityManager.persist(centroEd);
    }
}
