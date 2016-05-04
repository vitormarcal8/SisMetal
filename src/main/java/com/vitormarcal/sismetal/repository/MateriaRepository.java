package com.vitormarcal.sismetal.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.vitormarcal.sismetal.model.Materia;
import com.vitormarcal.sismetal.repository.filter.MateriaFilter;
import com.vitormarcal.sismetal.service.NegocioException;
import com.vitormarcal.sismetal.util.jpa.Transactional;

public class MateriaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Materia guardar(Materia materia) {
		return manager.merge(materia);
	}

	@Transactional
	public void remover(Materia materia) {
		try {
			materia = porId(materia.getId());
			manager.remove(materia);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Matéria-prima não pôde ser excluída.");
		}
	}

	public Materia porId(Long id) {
		return manager.find(Materia.class, id);
	}

	public Materia porNome(String nome) {
		try {
			return manager.createQuery("from Materia where upper(nome) like :nome", Materia.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Materia> filtrados(MateriaFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Materia.class);
		
		if ((!(null == filtro.getId()))) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(filtro.getCodigo())) {
			criteria.add(Restrictions.ilike("codigo", filtro.getCodigo()));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

}
