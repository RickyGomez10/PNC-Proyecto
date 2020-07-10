package com.uca.capas.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "materia")
public class Materia {
    @Id
    @Column(name = "id_materia")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMateria;

    @Column(name = "nombre")
    @Size(min=1, max=50,message = "El campo debe contener al menos 1 caracter y máximo 50.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String nombre;

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(mappedBy = "materia")
    private Set<MateriaXEstudiante> cruz = new HashSet<>();

    @Transient
    private Integer cMateria;

    public String getEstadoDelegate(){
        if(this.estado == null){
            return "";
        }
        else{
            if(this.estado) return "Activo";
            else return "Inactivo";
        }
    }

    public Integer getcMateria() { return cMateria; }

    public void setcMateria(Integer cMateria) { this.cMateria = cMateria; }

    public Materia() {

    }

    public Materia(Integer idMateria, String nombre, Boolean estado, Set<MateriaXEstudiante> cruz, Integer cMateria) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.estado = estado;
        this.cruz = cruz;
        this.cMateria = cMateria;
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

    @Override
    public String toString() {
        return "Materia{" +
                "idMateria=" + idMateria +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}
