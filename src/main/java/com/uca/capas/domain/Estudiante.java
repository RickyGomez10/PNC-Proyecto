package com.uca.capas.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(generator="estudiante_id_estudiante_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "estudiante_id_estudiante_seq", sequenceName = "public.estudiante_id_estudiante_seq", allocationSize = 1)
    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @Column(name = "carne")
    @Size(min=8, max=8, message = "El carné debe tener exactamente 8 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String carne;

    @Column(name = "nombre")
    @Size(min=1, max=50, message = "El nombre debe tener entre 1 y 50 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String nombre;

    @Column(name = "apellido")
    @Size(min=1, max=50, message = "El apellido debe tener entre 1 y 50 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String apellido;

    @Column(name = "carne_min")
    @Size(min=9, max=9, message = "El carné de minoridad debe tener 9 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String carneMin;

    @Column(name = "fecha_nac")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha de ahora.")
    @NotNull(message = "Este campo no puede estar vacío.")
    private Date fechaNac;

    @Column(name = "direccion")
    @Size(min=1, max=200, message = "La dirección debe tener entre 1 y 200 caracteres.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String direccion;

    @Column(name = "tel_movil")
    @Pattern(regexp = "^[0-9]{8}$", message = "El número de teléfono debe contener exactamente 8 dígitos.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String telefonoMovil;

    @Column(name = "tel_fijo")
    @Pattern(regexp = "^[0-9]{8}$", message = "El número de teléfono debe contener exactamente 8 dígitos.")
    @NotBlank(message = "Este campo no puede estar vacío.")
    private String telefonoFijo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro_ed")
    private CentroEd centroEd;

    @Column(name = "nombre_mama")
    @Size(max=100, message = "El nombre no debe tener más de 100 caracteres.")
    private String nombreMama;

    @Column(name = "nombre_papa")
    @Size(max=100, message = "El nombre no debe tener más de 100 caracteres.")
    private String nombrePapa;

    @OneToMany(mappedBy = "estudiante")
    private Set<MateriaXEstudiante> cruz = new HashSet<>();

    @Transient
    private Integer cCentroEd;

    public Estudiante() {
    }

    public Estudiante(String carne, String nombre, String apellido, String carneMin, Date fechaNac, String direccion, String telefonoMovil, String telefonoFijo, CentroEd centroEd, String nombreMama, String nombrePapa, Set<MateriaXEstudiante> cruz) {
        this.carne = carne;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carneMin = carneMin;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.telefonoMovil = telefonoMovil;
        this.telefonoFijo = telefonoFijo;
        this.centroEd = centroEd;
        this.nombreMama = nombreMama;
        this.nombrePapa = nombrePapa;
        this.cruz = cruz;
    }

    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccin) {
        this.direccion = direccin;
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
        return nombreMama.equals("")?"N/A":nombreMama;
    }

    public void setNombreMama(String nombreMama) { this.nombreMama = nombreMama; }

    public String getNombrePapa() { return nombrePapa.equals("")?"N/A":nombrePapa; }

    public void setNombrePapa(String nombrePapa) {
        this.nombrePapa = nombrePapa;
    }

    public Set<MateriaXEstudiante> getCruz() {
        return cruz;
    }

    public void setCruz(Set<MateriaXEstudiante> cruz) {
        this.cruz = cruz;
    }

    public Integer getcCentroEd() {
        return cCentroEd;
    }

    public void setcCentroEd(Integer cCentroEd) {
        this.cCentroEd = cCentroEd;
    }

    public Integer getEdad(){

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getFechaNac().getTime());

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        LocalDate bd = LocalDate.of(year, month, date);

        LocalDate now = LocalDate.now();

        return Period.between(bd, now).getYears();

    }

    public String getFechaNacDelegate() {
        if (fechaNac == null){
            return "";
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(fechaNac.getTime());
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "idEstudiante=" + idEstudiante +
                ", carne='" + carne + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", carneMin='" + carneMin + '\'' +
                ", fechaNac=" + fechaNac +
                ", direccion='" + direccion + '\'' +
                ", telefonoMovil='" + telefonoMovil + '\'' +
                ", telefonoFijo='" + telefonoFijo + '\'' +
                ", nombreMama='" + nombreMama + '\'' +
                ", nombrePapa='" + nombrePapa + '\'' +
                '}';
    }
}
