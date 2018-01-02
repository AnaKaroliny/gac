package br.com.gac.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gac.model.Usuario;
import br.com.gac.security.Logado;

@Named
@ViewScoped
public class DashboardBean implements Serializable {

	private static final long serialVersionUID = -74357325170790897L;

	@Inject @Logado
	private Usuario usuarioLogado;

	private String matricula;

	@PostConstruct
	public void init() {
		matricula = usuarioLogado.getMatricula();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}