package com.uca.capas.repositories;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepo extends JpaRepository<Materia, Integer> {
}
