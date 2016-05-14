package com.vitormarcal.sismetal.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.vitormarcal.sismetal.model.ItemPeca;
import com.vitormarcal.sismetal.model.Materia;
import com.vitormarcal.sismetal.model.Peca;
import com.vitormarcal.sismetal.repository.MateriaRepository;
import com.vitormarcal.sismetal.service.CadastroPecaService;
import com.vitormarcal.sismetal.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPecaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPecaService pecaService;

	@Inject
	private MateriaRepository materiaRepository;
	
	@Produces
	@PecaEdicao
	private Peca peca;
	
	private Materia materiaLinhaEditavel;
	private String codigo;

	public CadastroPecaBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.peca.adicionarItemVazio();

			this.recalcularPeca();
		}
	}

	private void limpar() {
		peca = new Peca();
	}
	
	public void pecaAlterado(@Observes PecaAlteradoEvent event){
		this.peca = event.getPeca();
	}

	public void salvar() {
		peca.removerItemVazio();
		try {
			this.peca = this.pecaService.salvar(this.peca);
		
			FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
		} finally {
			this.peca.adicionarItemVazio();
		}
	
	}

	private void recalcularPeca() {
		if (this.peca != null) {
			peca.recalcularValorTotal();
		}
	}

	public void carregarMateriaPorCodigo() {
		if (StringUtils.isNotEmpty(this.codigo)) {
			materiaLinhaEditavel = materiaRepository.porCodigo(codigo);
			carregarMateriaLinhaEditavel();
		}
	}

	public void carregarMateriaLinhaEditavel() {
		ItemPeca item = this.peca.getItens().get(0);

		if (this.materiaLinhaEditavel != null) {
			if (this.existeItemComMateria(this.materiaLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
			} else {
				item.setMateria(this.materiaLinhaEditavel);
				item.setValorUnitario(this.materiaLinhaEditavel.getValorUnitario());

				this.peca.adicionarItemVazio();
				this.materiaLinhaEditavel = null;
				this.codigo = null;

				this.peca.recalcularValorTotal();
			}
		}

	}

	public boolean existeItemComMateria(Materia materia) {
		boolean existeItem = false;

		for (ItemPeca item : this.getPeca().getItens()) {
			if (materia.equals(item.getMateria())) {
				existeItem = true;
				break;
			}
		}
		return existeItem;
	}

	public List<Materia> completarMateria(String nome) {
		return materiaRepository.buscaNomes(nome);
	}
	
	public void atualizarQuantidade(ItemPeca item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPeca().getItens().remove(linha);
			}
		}
		
		this.peca.recalcularValorTotal();
	}
	
	public boolean isEditando() {
		return this.peca.getId() != null;
	}

	public Peca getPeca() {
		return peca;
	}

	public void setPeca(Peca peca) {
		this.peca = peca;
	}

	public Materia getMateriaLinhaEditavel() {
		return materiaLinhaEditavel;
	}

	public void setMateriaLinhaEditavel(Materia materiaLinhaEditavel) {
		this.materiaLinhaEditavel = materiaLinhaEditavel;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
