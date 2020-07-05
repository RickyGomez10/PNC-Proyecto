package com.uca.capas.dao;

import com.uca.capas.domain.MateriaXEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MateriaXEstudianteDAOImpl implements MateriaXEstudianteDAO {
    @PersistenceContext(unitName = "capas")
    private EntityManager entityManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String sqlu = "UPDATE public.materia_x_estudiante SET anio=?, ciclo=?, nota=? WHERE id_materia=? AND id_estudiante=?";

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

    @Override
    public void modificar(MateriaXEstudiante mxe) throws DataAccessException {
        Object[] parametros = new Object[] {mxe.getAnio(), mxe.getCiclo(), mxe.getNota(), mxe.getMateria().getIdMateria(), mxe.getEstudiante().getIdEstudiante()};
        jdbcTemplate.update(sqlu, parametros);
    }
}
