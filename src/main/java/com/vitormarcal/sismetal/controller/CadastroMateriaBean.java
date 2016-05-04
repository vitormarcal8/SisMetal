package com.vitormarcal.sismetal.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vitormarcal.sismetal.model.Materia;
import com.vitormarcal.sismetal.service.CadastroMateriaService;
import com.vitormarcal.sismetal.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMateriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Materia materia;

	@Inject
	private CadastroMateriaService service;

	public CadastroMateriaBean() {
		limpar();
	}

	private void limpar() {
		materia = new Materia();
	}

	public void salvar() {
		materia = service.salvar(materia);
		limpar();

		FacesUtil.addInfoMessage("Matéria-Prima salva com sucesso.");
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
	public boolean isEditando() {
		return materia.getId() != null;
	}
}
