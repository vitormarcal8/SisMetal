package com.vitormarcal.sismetal.model;

public enum StatusPeca {

	ORCAMENTO("Orçamento"), EMITIDO("Emitido"), CANCELADO("Cancelado");

	private String descricao;
	
	StatusPeca(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
