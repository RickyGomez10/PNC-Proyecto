package com.uca.capas.service;

import com.uca.capas.domain.MateriaXEstudiante;
import com.uca.capas.domain.MateriaXEstudianteKey;
import org.springframework.dao.DataAccessException;
import java.util.List;

public interface MateriaXEstudianteService {

    public List<MateriaXEstudiante> findAll() throws DataAccessException;

    public MateriaXEstudiante findOne(MateriaXEstudianteKey id) throws DataAccessException;

    public void insertar(MateriaXEstudiante mxe) throws DataAccessException;

}
