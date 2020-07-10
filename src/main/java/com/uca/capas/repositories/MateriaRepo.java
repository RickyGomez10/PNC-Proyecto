package com.uca.capas.repositories;


import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface MateriaRepo extends JpaRepository<Materia, Integer> {

    @Query(value = "SELECT * FROM public.materia WHERE estado = true ORDER BY nombre ASC", nativeQuery = true)
    public ArrayList<Materia> findAllActive();

}
