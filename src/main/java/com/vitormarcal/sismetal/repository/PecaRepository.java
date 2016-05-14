package com.vitormarcal.sismetal.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.vitormarcal.sismetal.model.Materia;
import com.vitormarcal.sismetal.model.Peca;
import com.vitormarcal.sismetal.repository.filter.PecaFilter;

public class PecaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Peca guardar(Peca peca) {
		return this.manager.merge(peca);
	}

	public Peca porId(Long id) {
		return this.manager.find(Peca.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Peca> filtrados(PecaFilter filtro) {
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
