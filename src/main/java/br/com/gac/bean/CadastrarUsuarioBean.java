package br.com.gac.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gac.exception.RegistroExistenteException;
import br.com.gac.infra.jsf.FacesUtil;
import br.com.gac.infra.jsf.MessagesHelper;
import br.com.gac.model.GrupoUsuario;
import br.com.gac.model.Usuario;
import br.com.gac.service.GrupoUsuarioService;
import br.com.gac.service.UsuarioService;

@Named
@ViewScoped
public class CadastrarUsuarioBean implements Serializable {

	private static final long serialVersionUID = 4089832954506659515L;

	@Inject
	private UsuarioService service;

	@Inject
	private GrupoUsuarioService grupoUsuarioService;

	@Inject
	private MessagesHelper helper;

	@Inject
	private FacesUtil facesUtil;

	private List<GrupoUsuario> grupos;

	private Usuario usuario = new Usuario();

	@PostConstruct
	public void init() {
		String matricula = facesUtil.getParamMatricula("id");

		if (matricula != null)
			usuario = service.findUsuarioComGruposByMatricula(matricula);
	}

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

	public List<GrupoUsuario> getGrupos() {
		if (grupos == null)
			grupos = grupoUsuarioService.findAll();

		return grupos;
	}

}