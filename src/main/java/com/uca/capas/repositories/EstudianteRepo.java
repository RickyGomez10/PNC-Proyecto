package com.uca.capas.repositories;

import com.uca.capas.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepo extends JpaRepository<Estudiante, String> {
}
