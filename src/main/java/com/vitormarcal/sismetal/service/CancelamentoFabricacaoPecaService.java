package com.vitormarcal.sismetal.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vitormarcal.sismetal.model.Peca;
import com.vitormarcal.sismetal.model.StatusPeca;
import com.vitormarcal.sismetal.repository.PecaRepository;

public class CancelamentoFabricacaoPecaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PecaRepository pecaRepository;
	
	@Inject
	private EstoqueService estoqueService; 
	
	@com.vitormarcal.sismetal.util.jpa.Transactional
	public Peca cancelar(Peca peca) {
		peca = this.pecaRepository.porId(peca.getId());
		
		
		if(peca.isNaoCancelavel()){
			throw new NegocioException("peca não pode ser cancelado no status "
					+ peca.getStatus().getDescricao() + ".");
		}
		
		if(peca.isEmitido()){
			this.estoqueService.retornarItensEstoque(peca);
		}
		
		peca.setStatus(StatusPeca.CANCELADO);
		peca = this.pecaRepository.guardar(peca);
		return peca;
	}
	
}
