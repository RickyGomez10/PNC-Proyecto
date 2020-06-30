package com.uca.capas.dao;

import com.uca.capas.domain.Estudiante;

import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class EstudianteDAOImpl implements EstudianteDAO {

    @PersistenceContext(unitName="capas")
    private EntityManager entityManager;

    @Override
    public List<Estudiante> findAll() throws DataAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from public.estudiante");
        Query query = entityManager.createNativeQuery(sb.toString(), Estudiante.class);
        List<Estudiante> resultset = query.getResultList();
        return resultset;
    }



    @Override
    @Transactional
    public void insertar(Estudiante estudiante) throws DataAccessException {
        entityManager.persist(estudiante);
    }
}
