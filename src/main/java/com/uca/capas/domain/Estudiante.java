package com.uca.capas.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(schema = "public", name = "estudiante")
public class Estudiante {
    @Id
    @Column(name = "carne")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String carne;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "carne_min")
    private String carneMin;

    @Column(name = "fecha_nac")
    private Date fechaNac;

    @Column(name = "direccion")
    private String direccin;

    @Column(name = "tel_movil")
    private String telefonoMovil;

    @Column(name = "tel_fijo")
    private String telefonoFijo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro_ed")
    private CentroEd centroEd;

    @Column(name = "nombre_mama")
    private String nombreMama;

    @Column(name = "nombre_papa")
    private String nombrePapa;

    @OneToMany(mappedBy = "estudiante")
    Set<MateriaXEstudiante> cruz;

    public Estudiante() {
    }

    public Estudiante(String carne, String nombre, String apellido, String carneMin, Date fechaNac, String direccin, String telefonoMovil, String telefonoFijo, CentroEd centroEd, String nombreMama, String nombrePapa, Set<MateriaXEstudiante> cruz) {
        this.carne = carne;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carneMin = carneMin;
        this.fechaNac = fechaNac;
        this.direccin = direccin;
        this.telefonoMovil = telefonoMovil;
        this.telefonoFijo = telefonoFijo;
        this.centroEd = centroEd;
        this.nombreMama = nombreMama;
        this.nombrePapa = nombrePapa;
        this.cruz = cruz;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarneMin() {
        return carneMin;
    }

    public void setCarneMin(String carneMin) {
        this.carneMin = carneMin;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccin() {
        return direccin;
    }

    public void setDireccin(String direccin) {
        this.direccin = direccin;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public CentroEd getCentroEd() {
        return centroEd;
    }

    public void setCentroEd(CentroEd centroEd) {
        this.centroEd = centroEd;
    }

    public String getNombreMama() {
        return nombreMama;
    }

    public void setNombreMama(String nombreMama) {
        this.nombreMama = nombreMama;
    }

    public String getNombrePapa() {
        return nombrePapa;
    }

    public void setNombrePapa(String nombrePapa) {
        this.nombrePapa = nombrePapa;
    }

    public Set<MateriaXEstudiante> getCruz() {
        return cruz;
    }

    public void setCruz(Set<MateriaXEstudiante> cruz) {
        this.cruz = cruz;
    }
}
