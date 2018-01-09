package br.com.gac.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gac.constantes.Perfil;
import br.com.gac.exception.RegistroExistenteException;
import br.com.gac.infra.jsf.MessagesHelper;
import br.com.gac.model.Usuario;
import br.com.gac.service.UsuarioService;

@Named
@ViewScoped
public class CadastrarUsuarioBean implements Serializable {

	private static final long serialVersionUID = 4089832954506659515L;

	@Inject
	private UsuarioService service;

	@Inject
	private MessagesHelper helper;

	private Usuario usuario = new Usuario();

	public String salvar() {
		try {
			service.saveOrUpdate(usuario);
			helper.addFlash("Usuário salvo com sucesso!");
			return "/private/usuarios/pesquisar-usuarios.xhtml?faces-redirect=true";
		} catch (RegistroExistenteException e) {
			helper.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			helper.addErrorMessage("Erro ao salvar o usuário!");
		}
		return null;
	}

	public String atualizar() {
		try {
			service.saveOrUpdate(usuario);
			helper.addFlash("Usuário atualizado com sucesso!");
			return "/private/usuarios/pesquisar-usuarios.xhtml?faces-redirect=true";
		} catch (Exception e) {
			helper.addErrorMessage("Erro ao atualizar o usuário!");
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil[] getPerfis() {
		return Perfil.values();
	}
}