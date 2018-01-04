package br.com.gac.bean;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import br.com.gac.model.Arquivo;

@Named
@ViewScoped
public class ArquivoBean implements Serializable {

	private static final long serialVersionUID = 2038819097355392380L;

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

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

}
