package com.vitormarcal.sismetal.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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

	public List<Peca> filtrados(PecaFilter filtro) {
		// TODO Auto-generated method stub
		return null;
	}

}
