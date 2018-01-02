package br.com.gac.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gac.dao.GrupoUsuarioDAO;
import br.com.gac.dao.UsuarioDAO;
import br.com.gac.dao.datamodel.UsuarioDataModel;
import br.com.gac.dao.filter.UsuarioFilter;
import br.com.gac.infra.jsf.MessagesHelper;
import br.com.gac.model.GrupoUsuario;
import br.com.gac.model.Usuario;
import br.com.gac.service.UsuarioService;
import br.com.gac.util.CpfUtil;
import br.com.gac.util.Criptografia;

@Named
@ViewScoped
public class PesquisarUsuariosBean implements Serializable {

	private static final long serialVersionUID = -1013617852941176553L;

	@Inject
	private UsuarioDAO dao;

	@Inject
	private UsuarioService service;

	@Inject
	private GrupoUsuarioDAO grupoDAO;

	@Inject
	private MessagesHelper helper;

	@Inject
	private CpfUtil cpfUtil;

	private Usuario selecionado;

	private UsuarioDataModel usuarios;

	private List<GrupoUsuario> grupos;

	private UsuarioFilter filter = new UsuarioFilter();

	@PostConstruct
	public void pesquisar() {
		this.usuarios = new UsuarioDataModel(dao, filter);
	}

	public void mudarStatus() {
		try {
			service.mudarStatus(selecionado);
			helper.addInfoMessage("Status alterado com sucesso!");
		} catch (Exception e) {
			helper.addErrorMessage("Erro ao alterar o status do usuário!");
		}
	}

	public void resetarSenha() {
		String CPF = cpfUtil.somenteNumeros(selecionado.getCpf());
		selecionado.setSenha(new Criptografia().criptografar(CPF));
		selecionado.setMudarSenha(true);
		service.saveOrUpdate(selecionado);
		helper.addInfoMessage("Senha resetada com sucesso!");
	}

	public UsuarioDataModel getUsuarios() {
		return usuarios;
	}

	public UsuarioFilter getFilter() {
		return filter;
	}

	public List<GrupoUsuario> getGrupos() {
		if (grupos == null)
			this.grupos = grupoDAO.findAll();

		return grupos;
	}

	public Usuario getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Usuario selecionado) {
		this.selecionado = selecionado;
	}
}