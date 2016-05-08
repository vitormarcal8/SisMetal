
package com.vitormarcal.sismetal.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.vitormarcal.sismetal.model.Peca;
import com.vitormarcal.sismetal.service.OrdemFabricacaoPecaService;
import com.vitormarcal.sismetal.util.jsf.FacesUtil;

@Named
@RequestScoped
public class OrdemFabricacaoPecaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrdemFabricacaoPecaService ordemFabricacaoPecaService;
	
	@Inject
	@PecaEdicao
	private Peca peca;
	
	@Inject
	private Event<PecaAlteradoEvent> pecaAlteradoEvent;
	
	public void emitirPeca(){
		this.peca.removerItemVazio();
		try {
			this.peca = this.ordemFabricacaoPecaService.emitir(this.peca);
			//lançar um envento CDI
			this.pecaAlteradoEvent.fire(new PecaAlteradoEvent(this.peca));
			
			FacesUtil.addInfoMessage("Ordenada fabricação da peca com sucesso!");
		} finally {
			this.peca.adicionarItemVazio();
		}
	}
}
