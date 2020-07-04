package com.uca.capas.repositories;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.Usuario;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudianteRepo extends JpaRepository<Estudiante, Integer> {

    public Estudiante findEstudianteByCarne(String carne) throws DataAccessException;

    public List<Estudiante> findEstudiantesByApellidoContains(String val) throws DataAccessException;

    public List<Estudiante> findEstudiantesByNombreContains(String val) throws DataAccessException;

}
