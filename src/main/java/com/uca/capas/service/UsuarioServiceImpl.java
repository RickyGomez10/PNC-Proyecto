package com.uca.capas.service;

import java.util.List;

import javax.transaction.Transactional;

import com.uca.capas.dao.UsuarioDAO;
import com.uca.capas.repositories.MunicipioRepo;
import com.uca.capas.utils.Encriptador;
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
	private MunicipioRepo municipioRepo;

	@Autowired
	UsuarioDAO usuarioDAO;

	@Override
	public List<Usuario> findAll() throws DataAccessException {
		return usuarioRepo.findAll();
	}

	//Encontrar usuario por ID
	public Usuario findUsuarioById(String user) throws DataAccessException {
		return usuarioRepo.findUsuarioById(user);
	}

	@Transactional
	public void save(Usuario usuario) throws DataAccessException {

		usuario.setMunicipio(municipioRepo.getOne(usuario.getcMunicipio()));
		usuario.setRol(0); //0 = coordinador
		usuario.setSesion(false);
		usuario.setEstado(false);
		usuario.setClave(Encriptador.encriptar(usuario.getClave()));

		System.out.println(usuario.toString());
		usuarioDAO.save(usuario);

	}


}
