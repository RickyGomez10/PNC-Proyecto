package com.uca.capas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "departamento")
public class Departamento {
    @Id
    @Column(name = "id_depto")
    private Integer idDepto;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<Municipio> municipios;

    public Departamento() {

    }

    public Departamento(Integer idDepto, String nombre, List<Municipio> municipios) {
        this.idDepto = idDepto;
        this.nombre = nombre;
        this.municipios = municipios;
    }

    public Integer getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
        this.municipios = municipios;
    }
}
