package com.vitormarcal.sismetal.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.vitormarcal.sismetal.model.Cliente;
import com.vitormarcal.sismetal.model.TipoPessoa;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	
	public CadastroClienteBean() {
		limpar();
	}
	
	private void limpar(){
		cliente = new Cliente();
	}
	
	public void salvar() {
		//TODO Implementar o salvar cliente
	}
	
	public TipoPessoa[] getTipoPessoa(){
		return TipoPessoa.values();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
