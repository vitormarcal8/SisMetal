package com.vitormarcal.sismetal.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.vitormarcal.sismetal.model.Materia;
import com.vitormarcal.sismetal.repository.MateriaRepository;
import com.vitormarcal.sismetal.util.jpa.Transactional;

public class CadastroMateriaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	MateriaRepository repository;

	@Transactional
	public Materia salvar(Materia materia) {
		Materia materiaExistente = repository.porNome(materia.getNome());

		if (materiaExistente != null && !materiaExistente.equals(materia)) {
			throw new NegocioException("Já existe uma máteria-prima com o nome informado!");
		}

		return repository.guardar(materia);
	}

}
