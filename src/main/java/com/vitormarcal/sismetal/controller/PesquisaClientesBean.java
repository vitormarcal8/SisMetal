package com.vitormarcal.sismetal.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vitormarcal.sismetal.model.Cliente;
import com.vitormarcal.sismetal.repository.ClienteRepository;
import com.vitormarcal.sismetal.repository.filter.ClienteFilter;
import com.vitormarcal.sismetal.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository repository;

	private ClienteFilter filtro;
	private List<Cliente> clientesFiltrados;
	private Cliente clienteSelecionado;

	public PesquisaClientesBean() {
		filtro = new ClienteFilter();
	}

	public void pesquisar() {
		clientesFiltrados = repository.filtrados(filtro);
	}

	public void excluir() {
		repository.remover(clienteSelecionado);
		clientesFiltrados.remove(clienteSelecionado);

		FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getId() + " excluído com sucesso.");
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

}
