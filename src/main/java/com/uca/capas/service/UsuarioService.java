package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Usuario;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioService {

	public String findPasswordById(String user) throws DataAccessException;

}
