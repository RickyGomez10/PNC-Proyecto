package com.uca.capas.dao;


import com.uca.capas.domain.Estudiante;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface EstudianteDAO {

    public List<Estudiante> findAll() throws DataAccessException;

    public void insertar(Estudiante estudiante) throws DataAccessException;
}
