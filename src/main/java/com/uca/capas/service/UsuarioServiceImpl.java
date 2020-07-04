package com.uca.capas.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.uca.capas.dao.UsuarioDAO;
import com.uca.capas.repositories.MunicipioRepo;
import com.uca.capas.utils.Encriptador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
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
		return usuarioRepo.findAll(Sort.by(Sort.Direction.ASC, "idUsuario"));
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

	@Override
	@Transactional
	public void sesionUpdate(Usuario user) throws DataAccessException {
		usuarioDAO.save(user);
	}

	@Transactional
	public void update(Usuario usuario) throws DataAccessException {
		//Obtener todos los datos del usuario a actualizar
		Usuario usuario2 = usuarioDAO.findOne(usuario.getIdUsuario());

		usuario.setEstado(usuario2.getEstado());
		usuario.setRol(usuario2.getRol());
		usuario.setSesion(usuario2.getSesion());
		//Si el campo de nueva contraseña esta vacio entonces no se actualiza.
		if(usuario.getPassword().isEmpty() || usuario.equals("")){
			usuario.setClave(usuario2.getClave());
		}else{
			usuario.setClave(Encriptador.encriptar(usuario.getPassword()));
		}
		usuario.setMunicipio(municipioRepo.getOne(usuario.getcMunicipio()));

		System.out.println(usuario.toString());
		usuarioRepo.save(usuario);

	}


}
