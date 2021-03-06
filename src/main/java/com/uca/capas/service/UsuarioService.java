package com.uca.capas.service;

import java.util.List;

import com.uca.capas.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Usuario;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioService {
	public List<Usuario> findAll() throws DataAccessException;
	public Usuario findUsuarioById(String user) throws DataAccessException;
	public void save(Usuario usuario) throws DataAccessException;
	public void sesionUpdate(Usuario user) throws DataAccessException;
	public void update(Usuario usuario)throws  DataAccessException;
}
