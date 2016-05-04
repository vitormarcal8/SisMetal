package com.vitormarcal.sismetal.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vitormarcal.sismetal.model.Materia;
import com.vitormarcal.sismetal.repository.MateriaRepository;
import com.vitormarcal.sismetal.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Materia.class)
public class MateriaConverter implements Converter {

	//@Inject
	private MateriaRepository repository;
	
	public MateriaConverter() {
		this.repository = CDIServiceLocator.getBean(MateriaRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Materia retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = this.repository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Materia materia = (Materia) value;
			return materia.getId() == null ? null : materia.getId().toString();
		}
		return "";
	}

}