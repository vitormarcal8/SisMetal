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

import com.vitormarcal.sismetal.model.Funcionario;
import com.vitormarcal.sismetal.repository.filter.FuncionarioFilter;
import com.vitormarcal.sismetal.service.NegocioException;
import com.vitormarcal.sismetal.util.jpa.Transactional;

public class FuncionarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Funcionario guardar(Funcionario funcionario) {
		return manager.merge(funcionario);
	}

	@Transactional
	public void remover(Funcionario funcionario) {
		try {
			funcionario = porId(funcionario.getId());
			manager.remove(funcionario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Funcionário não pode ser excluído.");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> filtrados(FuncionarioFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Funcionario.class);

		if ((!(null == filtro.getId()))) {
			criteria.add(Restrictions.eq("id", filtro.getId()));
		}
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(filtro.getDocumentoReceitaFederal())) {
			criteria.add(Restrictions.eq("documentoReceitaFederal", filtro.getDocumentoReceitaFederal()));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Funcionario porId(Long id) {
		return manager.find(Funcionario.class, id);
	}

	public List<Funcionario> porNome(String nome) {
		return this.manager.createQuery("from Funcionario " + "where upper(nome) like :nome", Funcionario.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	public Funcionario porDocumentoReceitaFederal(String documentoReceitaFederal) {
		try {
			return this.manager
					.createQuery(
							"from Funcionario where upper(documentoReceitaFederal)" + " like :documentoReceitaFederal",
							Funcionario.class)
					.setParameter("documentoReceitaFederal", documentoReceitaFederal.toUpperCase()).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
