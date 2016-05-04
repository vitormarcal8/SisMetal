package com.vitormarcal.sismetal.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vitormarcal.sismetal.model.Cliente;
import com.vitormarcal.sismetal.repository.ClienteRepository;
import com.vitormarcal.sismetal.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteRepository repository;
	
	@Transactional
	public Cliente salvar(Cliente cliente){
		Cliente clienteExistente = repository.porDocumentoReceitaFederal(cliente.getDocumentoReceitaFederal());
		
		if(clienteExistente != null && !clienteExistente.equals(cliente)){
			throw new NegocioException("Já existe um Cliente com o documento informado.");
		}
		return repository.guardar(cliente);
	}
}
