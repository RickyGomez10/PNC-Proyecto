package com.uca.capas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="public",name="usuario")
public class Usuario {
	
	@Id
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="fecha_nac")
	private Date fechaNacimiento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_municipio")
	private Municipio municipio;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="estado")
	private Boolean estado;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="rol")
	private Integer rol;

	
	public Usuario(String usuario, String nombre, String apellido, Date fechaNacimiento, Municipio municipio,
			String direccion, Boolean estado, String clave, Integer rol) {
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

	public String getDireccion() {
		return direccion;
	}

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
	
	

}
