package com.uca.capas.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "public", name = "estudiante")
public class Materia {
    @Id
    @Column(name = "id_materia")
    private Integer idMateria;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(mappedBy = "materia")
    Set<MateriaXEstudiante> cruz;

    public Materia() {

    }

    public Materia(Integer idMateria, String nombre, Boolean estado, Set<MateriaXEstudiante> cruz) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.estado = estado;
        this.cruz = cruz;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Set<MateriaXEstudiante> getCruz() {
        return cruz;
    }

    public void setCruz(Set<MateriaXEstudiante> cruz) {
        this.cruz = cruz;
    }
}
