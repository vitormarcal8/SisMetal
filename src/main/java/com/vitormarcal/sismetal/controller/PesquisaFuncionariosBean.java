package com.vitormarcal.sismetal.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vitormarcal.sismetal.model.Funcionario;
import com.vitormarcal.sismetal.repository.FuncionarioRepository;
import com.vitormarcal.sismetal.repository.filter.FuncionarioFilter;
import com.vitormarcal.sismetal.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFuncionariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioRepository repository;

	private FuncionarioFilter filtro;
	private List<Funcionario> funcionariosFiltrados;
	private Funcionario funcionarioSelecionado;

	public PesquisaFuncionariosBean() {
		filtro = new FuncionarioFilter();
	}

	public void excluir() {
		repository.remover(funcionarioSelecionado);
		funcionariosFiltrados.remove(funcionarioSelecionado);

		FacesUtil.addInfoMessage("Funcionario " + funcionarioSelecionado.getId() + " excluído com sucesso.");
	}

	public void pesquisar() {
		funcionariosFiltrados = repository.filtrados(filtro);
	}

	public List<Funcionario> getFuncionariosFiltrados() {
		return funcionariosFiltrados;
	}

	public FuncionarioFilter getFiltro() {
		return filtro;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

}
