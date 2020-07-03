package com.uca.capas.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class MateriaXEstudiante {

    @EmbeddedId
    MateriaXEstudianteKey id;

    @ManyToOne
    @MapsId("id_materia")
    @JoinColumn(name = "id_materia")
    private Materia materia;

    @ManyToOne
    @MapsId("id_estudiante")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @Column(name = "anio")
    @NotBlank(message = "Este campo no puede estar vacío.")
    @Pattern(regexp = "^20[0-2][0-9]$", message = "Debe ingresar un año válido.")
    private String anio;

    @Column(name = "ciclo")
    @NotBlank(message = "Este campo no puede estar vacío.")
    @Pattern(regexp = "^0[1-3]$", message = "Debe ingresar un ciclo válido (01, 02 o 03).")
    private String ciclo;

    @Column(name = "nota")
    @Min(value = 0, message = "La nota mínima permitida es cero (0).")
    @Max(value = 10, message = "La nota máxima permitida es diez (10).")
    @NotNull(message = "Este campo no puede estar vacío.")
    private float nota;

    @Transient
    private Integer idEstudiante;

    @Transient
    @NotNull(message = "Este campo no puede estar vacío.")
    private Integer idMateria;

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

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getResultadoDelegate(){
        if(nota >= 6.0)
            return "Aprobada";
        return "Reprobada";
    }

    @Override
    public String toString() {
        return "MateriaXEstudiante{" +
                "anio='" + anio + '\'' +
                ", ciclo='" + ciclo + '\'' +
                ", nota=" + nota +
                ", idEstudiante=" + idEstudiante +
                ", idMateria=" + idMateria +
                '}';
    }
}
