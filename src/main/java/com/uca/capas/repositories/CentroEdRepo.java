package com.uca.capas.repositories;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CentroEdRepo extends JpaRepository<CentroEd, Integer> {

    @Query(value = "UPDATE public.centro_ed SET estado=?1 where id_centro_ed = ?2", nativeQuery = true)
    public CentroEd updateEstado(Boolean estado, Integer centroEd);

    @Query(value = "SELECT * FROM public.centro_ed WHERE estado = true", nativeQuery = true)
    public ArrayList<CentroEd> findAllActive();

}
