package br.com.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.gac.dao.UsuarioDAO;
import br.com.gac.infra.cdi.CDIServiceLocator;
import br.com.gac.model.Usuario;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	private UsuarioDAO usuarioDAO;

	public UsuarioConverter() {
		usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		if (value != null) {
			Long id = new Long(value);
			retorno = usuarioDAO.getPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		return "";
	}
}
