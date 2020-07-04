package com.uca.capas.repositories;

import com.uca.capas.domain.MateriaXEstudiante;
import com.uca.capas.domain.MateriaXEstudianteKey;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MateriaXEstudianteRepo extends JpaRepository<MateriaXEstudiante, MateriaXEstudianteKey> {

    @Query(value = "SELECT COUNT(id_materia) FROM public.materia_x_estudiante WHERE id_estudiante = ?1 AND nota >= 6.0", nativeQuery = true)
    public Integer getMateriasAprobadas(Integer id) throws DataAccessException;

    @Query(value = "SELECT COUNT(id_materia) FROM public.materia_x_estudiante WHERE id_estudiante = ?1 AND nota < 6.0", nativeQuery = true)
    public Integer getMateriasReprobadas(Integer id) throws DataAccessException;

    @Query(value = "SELECT AVG(nota) FROM public.materia_x_estudiante WHERE id_estudiante = ?1", nativeQuery = true)
    public Float getPromedio(Integer id) throws DataAccessException;

    @Query(value = "SELECT * FROM public.materia_x_estudiante WHERE id_estudiante = ?1", nativeQuery = true)
    public List<MateriaXEstudiante> getMaterias(Integer id);

}
