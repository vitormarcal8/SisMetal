package com.vitormarcal.sismetal.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vitormarcal.sismetal.model.Materia;
import com.vitormarcal.sismetal.repository.MateriaRepository;
import com.vitormarcal.sismetal.repository.filter.MateriaFilter;
import com.vitormarcal.sismetal.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMateriasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MateriaRepository repository;

	private MateriaFilter filtro;
	private Materia materiaSelecionada;
	private List<Materia> materiasFiltradas;

	public PesquisaMateriasBean() {
		filtro = new MateriaFilter();
	}
	
	public void pesquisar(){
		materiasFiltradas = repository.filtrados(filtro);
	}
	
	public void excluir(){
		repository.remover(materiaSelecionada);
		materiasFiltradas.remove(materiaSelecionada);
		
		FacesUtil.addInfoMessage("Matéria-Prima " + materiaSelecionada.getCodigo() + " excluída com sucesso.");
	}
	
	public MateriaFilter getFiltro() {
		return filtro;
	}

	public List<Materia> getMateriasFiltradas() {
		return materiasFiltradas;
	}

	public Materia getMateriaSelecionada() {
		return materiaSelecionada;
	}

	public void setMateriaSelecionada(Materia materiaSelecionada) {
		this.materiaSelecionada = materiaSelecionada;
	}

}
