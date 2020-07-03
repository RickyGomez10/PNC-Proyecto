package com.uca.capas.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "centro_ed")
public class CentroEd {

    @Id
    @Column(name = "id_centro_ed")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCentroEd;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @OneToMany(mappedBy = "centroEd", fetch = FetchType.LAZY)
    private List<Estudiante> estudiantes;

    @Transient
    private Integer cMunicipio;

    public String getEstadoDelegate(){
        if(this.estado == null){
            return "";
        }
        else{
            if(this.estado) return "activo";
            else return "inactivo";
        }
    }
    @Override
    public String toString() {
        return "CentroEd{" +
                "idCentroEd=" + idCentroEd +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                ", municipio=" + municipio +
                ", estudiantes=" + estudiantes +
                ", cMunicipio=" + cMunicipio +
                '}';
    }

    public CentroEd() {

    }

    public CentroEd(Integer idCentroEd, String nombre, Boolean estado, Municipio municipio, List<Estudiante> estudiantes, Integer cMunicipio) {
        this.idCentroEd = idCentroEd;
        this.nombre = nombre;
        this.estado = estado;
        this.municipio = municipio;
        this.estudiantes = estudiantes;
        this.cMunicipio = cMunicipio;

    }

    public Integer getcMunicipio() { return cMunicipio; }

    public void setcMunicipio(Integer cMunicipio) { this.cMunicipio = cMunicipio; }

    public Integer getIdCentroEd() {
        return idCentroEd;
    }

    public void setIdCentroEd(Integer idCentroEd) {
        this.idCentroEd = idCentroEd;
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
