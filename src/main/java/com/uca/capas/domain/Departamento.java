package com.uca.capas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="public",name="departamento")
public class Departamento {
	@Id
	@Column(name="id_depto")
	private Integer idDepto;
	
	@Column(name="nombre")
	private String nombre;
	
	@OneToMany(mappedBy="departamento", fetch = FetchType.LAZY)
	private List<Municipio> municipios;
	
	
}
