package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;


import com.uca.capas.domain.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;
	
	@Override
	public List<Usuario> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.usuario");
		Query query = entityManager.createNativeQuery(sb.toString(), Usuario.class);
		List<Usuario> resultset = query.getResultList();
		return resultset;
	}



	@Override
	public void insertar(Usuario usuario) throws DataAccessException {
		// TODO Auto-generated method stub
		entityManager.persist(usuario);
		
	}
	

}
