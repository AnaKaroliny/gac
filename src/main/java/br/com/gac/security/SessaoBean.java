package br.com.gac.security;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.gac.model.Usuario;
import br.com.gac.util.DataUtil;

@Named
@SessionScoped
public class SessaoBean implements Serializable {

	private static final long serialVersionUID = -2390195761526411399L;

	@Inject
	private ExternalContext external;

	@Inject
	private DataUtil dataUtil;

	private Usuario usuarioLogado;

	@PostConstruct
	public void init() {
		UsuarioSistema usuarioSistema = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) external.getUserPrincipal();

		if (isAutenticado(auth))
			usuarioSistema = (UsuarioSistema) auth.getPrincipal();

		usuarioLogado = usuarioSistema.getUsuario();
	}

	@Logado
	@Produces
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public String getNomeUsuarioLogado() {
		return getUsuarioLogado().getNome();
	}

	public boolean hasRole(String role) {
		return external.isUserInRole("ROLE_" + role);
	}

	private boolean isAutenticado(UsernamePasswordAuthenticationToken auth) {
		return auth != null && auth.getPrincipal() != null;
	}

	public int getQtdNotificacoes() {
		int count = 0;
		return count;
	}

	public Integer getAnoAtual() {
		return dataUtil.getAnoAtual();
	}
	
	public boolean isAdministrador() {
		return external.isUserInRole("ADMINISTRADOR");
	}
	
	public boolean isJuizAuditor() {
		return external.isUserInRole("JUIZ_AUDITOR");
	}
}