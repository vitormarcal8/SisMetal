package com.vitormarcal.sismetal.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vitormarcal.sismetal.model.Peca;
import com.vitormarcal.sismetal.repository.PecaRepository;
import com.vitormarcal.sismetal.repository.filter.PecaFilter;
import com.vitormarcal.sismetal.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaPecasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PecaRepository repository;

	private PecaFilter filtro;
	private List<Peca> pecasFiltrados;
	private Peca pecaSelecionado;

	public PesquisaPecasBean() {
		filtro = new PecaFilter();
	}

	public void pesquisar() {
		pecasFiltrados = repository.filtrados(filtro);
	}

	public List<Peca> getPecasFiltrados() {
		return pecasFiltrados;
	}

	public PecaFilter getFiltro() {
		return filtro;
	}

	public Peca getPecaSelecionado() {
		return pecaSelecionado;
	}

	public void setPecaSelecionado(Peca pecaSelecionado) {
		this.pecaSelecionado = pecaSelecionado;
	}

}
