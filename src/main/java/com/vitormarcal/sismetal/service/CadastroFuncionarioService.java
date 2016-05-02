package com.vitormarcal.sismetal.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vitormarcal.sismetal.model.Funcionario;
import com.vitormarcal.sismetal.repository.FuncionarioRepository;
import com.vitormarcal.sismetal.util.jpa.Transactional;

public class CadastroFuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioRepository repository;
	
	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		Funcionario funcionarioExistente = repository.porDocumentoReceitaFederal(funcionario.getDocumentoReceitaFederal());
		
		if(funcionarioExistente != null && !funcionarioExistente.equals(funcionario)){
			throw new NegocioException("Já existe um funcionário com o documento informado.");
		}
		return repository.guardar(funcionario);
	}

}
