package com.vitormarcal.sismetal.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vitormarcal.sismetal.model.Peca;
import com.vitormarcal.sismetal.repository.PecaRepository;
import com.vitormarcal.sismetal.util.jpa.Transactional;

public class CadastroPecaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PecaRepository repository;
	
	@Transactional
	public Peca salvar(Peca peca) {
		peca.recalcularValorTotal();
		
		if (peca.getItens().isEmpty()) {
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}
		peca = this.repository.guardar(peca);
		return peca;
	}

}
