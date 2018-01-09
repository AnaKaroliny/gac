package br.com.gac.bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.gac.model.Arquivo;

@Named
@ViewScoped
public class RecuperaArquivoPdfBean implements Serializable {

	private static final long serialVersionUID = 1378513445589387646L;

	private Arquivo arquivo = new Arquivo();

	public String abrirArquivo() {

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		try {
			response.reset();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "filename=\"" + arquivo.getNome() + "\";");
			response.setContentLength(arquivo.getArquivo().length);
			response.getOutputStream().write(arquivo.getArquivo(), 0, arquivo.getArquivo().length);
			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public StreamedContent getFile() {
		InputStream stream = new ByteArrayInputStream(arquivo.getArquivo());
		return new DefaultStreamedContent(stream, "application/pdf", arquivo.getNome());
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

}
