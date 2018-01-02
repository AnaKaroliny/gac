package br.com.gac.util;

import javax.faces.context.FacesContext;

public class BuscaNoWebContent {

	public static String buscaArquivo(String path) {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
	}
}