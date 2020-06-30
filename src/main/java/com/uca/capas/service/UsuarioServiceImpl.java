package com.uca.capas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import com.uca.capas.domain.Usuario;

import com.uca.capas.repositories.UsuarioRepo;


@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	
	
	//Encontrar usuario por ID
	public Usuario findUsuarioById(String codigo) throws DataAccessException {
		return usuarioRepo.findUsuarioById(codigo);
	}

}
