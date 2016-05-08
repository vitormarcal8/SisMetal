package com.vitormarcal.sismetal.controller;

import com.vitormarcal.sismetal.model.Peca;

public class PecaAlteradoEvent {

	private Peca peca;

	public PecaAlteradoEvent(Peca peca) {
		this.peca = peca;
	}
	
	public Peca getPeca() {
		return peca;
	}
}
