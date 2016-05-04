package com.vitormarcal.sismetal.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vitormarcal.sismetal.model.Cliente;
import com.vitormarcal.sismetal.model.TipoPessoa;
import com.vitormarcal.sismetal.service.CadastroClienteService;
import com.vitormarcal.sismetal.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroClienteService service;

	private Cliente cliente;

	public CadastroClienteBean() {
		limpar();
	}

	private void limpar() {
		cliente = new Cliente();
	}

	public void salvar() {
		cliente = service.salvar(cliente);
		limpar();

		FacesUtil.addInfoMessage("Cliente salvo com sucesso.");

	}
	
	public boolean isEditando() {
		return cliente.getId() != null;
	}

	public TipoPessoa[] getTipoPessoa() {
		return TipoPessoa.values();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
