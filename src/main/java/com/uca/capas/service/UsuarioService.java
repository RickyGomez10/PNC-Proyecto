package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Usuario;

public interface UsuarioService {
	
	//Encontrar usuario por ID
	public Usuario findUsuarioById(String codigo) throws Exception;
	
}
