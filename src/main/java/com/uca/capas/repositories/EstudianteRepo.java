package com.uca.capas.repositories;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstudianteRepo extends JpaRepository<Estudiante, Integer> {

    public Estudiante findEstudianteByCarne(String carne);

}
