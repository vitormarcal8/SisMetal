package com.vitormarcal.sismetal.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vitormarcal.sismetal.model.Peca;
import com.vitormarcal.sismetal.model.StatusPeca;
import com.vitormarcal.sismetal.repository.PecaRepository;
import com.vitormarcal.sismetal.util.jpa.Transactional;

public class OrdemFabricacaoPecaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPecaService cadastroPecaService;
	@Inject
	private PecaRepository pecaRepository;
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Peca emitir(Peca peca) {
		peca = this.cadastroPecaService.salvar(peca);
		 
		if(peca.isNaoEmissivel()){
			throw new NegocioException("Peca não pode ser emitido com status "
					+ peca.getStatus().getDescricao() + ".");
		}
		
		this.estoqueService.baixarItensEstoque(peca);
		
		peca.setStatus(StatusPeca.EMITIDO);
		
		peca = this.pecaRepository.guardar(peca);
		
		return peca;
	}

}
