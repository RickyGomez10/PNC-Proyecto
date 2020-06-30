package com.uca.capas.dao;

import com.uca.capas.domain.MateriaXEstudiante;
import org.springframework.dao.DataAccessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class MateriaXEstudianteDAOImpl implements MateriaXEstudianteDAO {
    @PersistenceContext(unitName = "capas")
    private EntityManager entityManager;

    @Override
    public List<MateriaXEstudiante> findAll() throws DataAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from public.materia_x_estudiante");
        Query query = entityManager.createNativeQuery(sb.toString(), MateriaXEstudiante.class);
        List<MateriaXEstudiante> resultset = query.getResultList();
        return resultset;
    }

    @Override
    public MateriaXEstudiante findOne(Integer code) throws DataAccessException {
        MateriaXEstudiante materiaEstudiante = entityManager.find(MateriaXEstudiante.class, code);

        return materiaEstudiante;

    }

    @Override
    @Transactional
    public void insertar(MateriaXEstudiante materiaEstudiante) throws DataAccessException {
        entityManager.persist(materiaEstudiante);
    }
}
