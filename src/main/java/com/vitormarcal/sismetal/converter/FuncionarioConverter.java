package com.vitormarcal.sismetal.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vitormarcal.sismetal.model.Funcionario;
import com.vitormarcal.sismetal.repository.FuncionarioRepository;
import com.vitormarcal.sismetal.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {

	//@Inject
	private FuncionarioRepository repository;
	
	public FuncionarioConverter() {
		this.repository = CDIServiceLocator.getBean(FuncionarioRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionario retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = this.repository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Funcionario funcionario = (Funcionario) value;
			return funcionario.getId() == null ? null : funcionario.getId().toString();
		}
		return "";
	}

}