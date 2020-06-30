package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
public class MateriaXEstudianteKey implements Serializable {

    @Column(name = "id_materia")
    Integer idMateria;

    @Column(name = "carne")
    String carne;

    public MateriaXEstudianteKey() {

    }

    public MateriaXEstudianteKey(Integer idMateria, String carne) {
        this.idMateria = idMateria;
        this.carne = carne;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }
}
