package com.uca.capas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(schema = "public", name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(generator="usuario_id_usuario_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "usuario_id_usuario_seq", sequenceName = "public.usuario_id_usuario_seq", allocationSize = 1)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "usuario")
    @Size(min=1, max=15, message = "El usuario debe tener entre 1 y 15 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String usuario;

    @Column(name = "nombre")
    @Size(min=1, max=15, message = "El nombre debe tener entre 1 y 50 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String nombre;

    @Column(name = "apellido")
    @Size(min=1, max=15, message = "El apellido debe tener entre 1 y 50 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String apellido;

    @Column(name = "fecha_nac")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha de ahora.")
    @NotNull(message = "Este campo no puede estar vacío.")
    private Date fechaNacimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @Transient
    private Integer cMunicipio;

    @Column(name = "direccion")
    @Size(min=1, max=15, message = "La dirección debe tener entre 1 y 200 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String direccion;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "clave")
    @Size(max=50, message = "La clave no debe tener más de 50 caracteres.")
    @Size(min=8, message = "La clave debe tener como mínimo 8 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String clave;

    @Column(name = "rol")
    private Integer rol;

    @Column(name = "sesion")
    private Boolean sesion;

    @Column(name = "fecha_sesion")
    private Date fechaSesion;


    public Usuario(String usuario, String nombre, String apellido, Date fechaNacimiento, Municipio municipio,
                   String direccion, Boolean estado, String clave, Integer rol, Boolean sesion) {
        super();
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.estado = estado;
        this.clave = clave;
        this.rol = rol;
        this.sesion = sesion;
    }

    public Usuario() {

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Integer getcMunicipio() { return cMunicipio; }

    public void setcMunicipio(Integer cMunicipio) { this.cMunicipio = cMunicipio; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public Boolean getSesion() {
        return sesion;
    }

    public void setSesion(Boolean sesion) {
        this.sesion = sesion;
    }

    public Date getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(Date fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public Integer getEdad(){

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getFechaNacimiento().getTime());

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        LocalDate bd = LocalDate.of(year, month, date);

        LocalDate now = LocalDate.now();

        return Period.between(bd, now).getYears();

    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", municipio=" + municipio +
                ", cMunicipio=" + cMunicipio +
                ", direccion='" + direccion + '\'' +
                ", estado=" + estado +
                ", clave='" + clave + '\'' +
                ", rol=" + rol +
                ", sesion=" + sesion +
                ", fechaSesion=" + fechaSesion +
                '}';
    }
}
