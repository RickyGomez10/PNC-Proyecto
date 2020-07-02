package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;


import com.uca.capas.domain.Usuario;
import org.springframework.stereotype.Repository;

@Repository
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
	@Transactional
	public void save(Usuario usuario) throws DataAccessException {
		entityManager.persist(usuario);
	}

}
