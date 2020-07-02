package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
public class MateriaXEstudianteKey implements Serializable {

    @Column(name = "id_materia")
    private Integer idMateria;

    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    public MateriaXEstudianteKey() {

    }

    public MateriaXEstudianteKey(Integer idMateria, Integer idEstudiante) {
        this.idMateria = idMateria;
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

}
