package com.vitormarcal.sismetal.model;

public interface Pessoa {
	
	void setId(Long id);
	void setNome(String nome);
	void setDocumentoReceitaFederal(String documentoReceitaFederal);
	void setEmail(String email);
	void setTipoPessoa(TipoPessoa tipoPessoa);

}
