package com.vitormarcal.sismetal.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Transient;

import com.vitormarcal.sismetal.model.ItemPeca;
import com.vitormarcal.sismetal.model.Peca;
import com.vitormarcal.sismetal.repository.PecaRepository;
import com.vitormarcal.sismetal.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PecaRepository pecaRepository;
	
	@Transactional
	public void baixarItensEstoque(Peca peca) {
			peca = this.pecaRepository.porId(peca.getId());
			
			for(ItemPeca item : peca.getItens()){
				item.getMateria().baixarEstoque(item.getQuantidade());
			}
	}

	@Transient
	public void retornarItensEstoque(Peca peca) {
		peca = this.pecaRepository.porId(peca.getId());
		
		for( ItemPeca item : peca.getItens()){
			item.getMateria().adicionarEstoque(item.getQuantidade());
		}
	}

}
