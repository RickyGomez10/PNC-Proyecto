package com.uca.capas.repositories;

import com.uca.capas.domain.MateriaXEstudiante;
import com.uca.capas.domain.MateriaXEstudianteKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaXEstudianteRepo extends JpaRepository<MateriaXEstudiante, MateriaXEstudianteKey> {
}
