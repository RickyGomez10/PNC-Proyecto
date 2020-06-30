package com.uca.capas.domain;

import javax.persistence.*;

@Entity
public class MateriaXEstudiante {

    @EmbeddedId
    MateriaXEstudianteKey id;

    @ManyToOne
    @MapsId("id_materia")
    @JoinColumn(name = "id_materia")
    Materia materia;

    @ManyToOne
    @MapsId("carne")
    @JoinColumn(name = "carne")
    Estudiante estudiante;

    @Column(name = "anio")
    String anio;

    @Column(name = "ciclo")
    String ciclo;

    @Column(name = "nota")
    float nota;

    public MateriaXEstudiante() {

    }

    public MateriaXEstudiante(MateriaXEstudianteKey id, Materia materia, Estudiante estudiante, String anio, String ciclo, float nota) {
        this.id = id;
        this.materia = materia;
        this.estudiante = estudiante;
        this.anio = anio;
        this.ciclo = ciclo;
        this.nota = nota;
    }

    public MateriaXEstudianteKey getId() {
        return id;
    }

    public void setId(MateriaXEstudianteKey id) {
        this.id = id;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
}
