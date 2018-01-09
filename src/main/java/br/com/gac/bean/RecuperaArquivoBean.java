package br.com.gac.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.gac.bo.ArquivoBO;
import br.com.gac.model.Arquivo;

@Named
@RequestScoped
public class RecuperaArquivoBean implements Serializable {

	private static final long serialVersionUID = 5470180419190809701L;

	private Arquivo arquivo = new Arquivo();

	@Inject
	private ArquivoBO arquivoBO;

	public StreamedContent getfoto() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		}
		String id = context.getExternalContext().getRequestParameterMap().get("id");
		if (!id.isEmpty()) {
			arquivo = arquivoBO.findArquivoById(Integer.parseInt(id));
			return new DefaultStreamedContent(new ByteArrayInputStream(arquivo.getArquivo()));
		}
		return null;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

}
