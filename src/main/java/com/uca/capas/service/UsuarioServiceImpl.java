package com.uca.capas.service;

import java.util.List;

import javax.transaction.Transactional;

import com.uca.capas.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import com.uca.capas.domain.Usuario;

import com.uca.capas.repositories.UsuarioRepo;


@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	UsuarioDAO usuarioDAO;

	//Encontrar usuario por ID
	public Usuario findUsuarioById(String user) throws DataAccessException {
		return usuarioRepo.findUsuarioById(user);
	}

	@Transactional
	public void save(Usuario usuario) throws DataAccessException {
		usuarioRepo.save(usuario);
	}


}
