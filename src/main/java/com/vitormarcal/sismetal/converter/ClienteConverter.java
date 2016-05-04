package com.vitormarcal.sismetal.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vitormarcal.sismetal.model.Cliente;
import com.vitormarcal.sismetal.repository.ClienteRepository;
import com.vitormarcal.sismetal.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

	//@Inject
	private ClienteRepository repository;
	
	public ClienteConverter() {
		this.repository = CDIServiceLocator.getBean(ClienteRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = this.repository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cliente cliente = (Cliente) value;
			return cliente.getId() == null ? null : cliente.getId().toString();
		}
		return "";
	}

}