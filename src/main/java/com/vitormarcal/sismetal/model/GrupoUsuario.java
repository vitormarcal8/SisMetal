package com.vitormarcal.sismetal.model;

public enum GrupoUsuario {

	GERENTE("Gerente"),  VENDEDOR("Vendedor");

	private String descricao;
	
	private GrupoUsuario(String descricao) {
		this.descricao  = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
