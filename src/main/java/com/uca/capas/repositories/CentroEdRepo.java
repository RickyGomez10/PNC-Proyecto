package com.uca.capas.repositories;

import com.uca.capas.domain.CentroEd;
import com.uca.capas.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CentroEdRepo extends JpaRepository<CentroEd, Integer> {

    @Query(value = "UPDATE public.centro_ed SET estado=?1 where id_centro_ed = ?2", nativeQuery = true)
    public CentroEd updateEstado(Boolean estado, Integer centroEd);

}
