package com.uca.capas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "municipio")
public class Municipio {
	@Id
	@Column(name = "id_municipio")
	private Integer idMunicipio;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
	private List<Usuario> usuarios;

	@OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
	private List<CentroEd> centros;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_depto")
	private Departamento departamento;

	public Municipio() {

	}

	public Municipio(Integer idMunicipio, String nombre, List<Usuario> usuarios, List<CentroEd> centros, Departamento departamento) {
		this.idMunicipio = idMunicipio;
		this.nombre = nombre;
		this.usuarios = usuarios;
		this.centros = centros;
		this.departamento = departamento;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<CentroEd> getCentros() {
		return centros;
	}

	public void setCentros(List<CentroEd> centros) {
		this.centros = centros;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
