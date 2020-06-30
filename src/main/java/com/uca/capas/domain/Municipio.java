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
@Table(schema="public",name="municipio")
public class Municipio {
	@Id
	@Column(name="id_municipio")
	private Integer idMunicipio;
	
	@Column(name="nombre")
	private String nombre;
	
	@OneToMany(mappedBy="municipio", fetch = FetchType.EAGER)
	private List<Usuario> usuarios;
	
	@OneToMany(mappedBy="municipio", fetch = FetchType.EAGER)
	private List<CentroEd> centros;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_depto")
	private Departamento departamento;
	

}
