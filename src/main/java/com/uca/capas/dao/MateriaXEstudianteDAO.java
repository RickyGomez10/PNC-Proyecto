package com.uca.capas.dao;

import com.uca.capas.domain.MateriaXEstudiante;
import com.uca.capas.domain.Usuario;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MateriaXEstudianteDAO {

    public List<MateriaXEstudiante> findAll() throws DataAccessException;

    public MateriaXEstudiante findOne(Integer code) throws DataAccessException;

    public void insertar(MateriaXEstudiante materiaEstudiante) throws DataAccessException;
}
