package com.vitormarcal.sismetal.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.vitormarcal.sismetal.model.Funcionario;
import com.vitormarcal.sismetal.model.GrupoUsuario;
import com.vitormarcal.sismetal.service.CadastroFuncionarioService;
import com.vitormarcal.sismetal.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	@Inject
	private CadastroFuncionarioService cadastroFuncionarioService;

	public CadastroFuncionarioBean() {
		limpar();
	}

	private void limpar() {
		funcionario = new Funcionario();
	}

	public void salvar() {
		funcionario = cadastroFuncionarioService.salvar(funcionario);
		limpar();
		
		FacesUtil.addInfoMessage("Funcionário salvo com sucesso.");
	}

	public boolean isEditando() {
		return funcionario.getId() != null;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public GrupoUsuario[] getGrupoUsuario() {
		return GrupoUsuario.values();
	}

}
