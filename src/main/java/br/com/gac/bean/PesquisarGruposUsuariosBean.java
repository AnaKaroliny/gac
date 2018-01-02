package br.com.gac.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.gac.infra.jsf.FacesUtil;
import br.com.gac.infra.jsf.MessagesHelper;
import br.com.gac.model.GrupoUsuario;
import br.com.gac.service.GrupoUsuarioService;
import br.com.gac.util.AES;

@Named
@ViewScoped
public class PesquisarGruposUsuariosBean implements Serializable {

	private static final long serialVersionUID = 3364978570787851689L;

	@Inject
	private GrupoUsuarioService service;

	@Inject
	private MessagesHelper helper;

	private List<GrupoUsuario> grupos;

	private GrupoUsuario grupo = new GrupoUsuario();

	private Integer id;

	@PostConstruct
	public void init() {
		pesquisar();
	}

	public void pesquisar() {
		grupos = service.pesquisar();
	}

	public void save() {
		service.save(grupo);

		helper.addInfoMessage("Grupo de Usuário salvo com sucesso!");
		FacesUtil.updateComponents("frm:msg", "frm:grupos-table");

		pesquisar();
	}

	public void mudarStatus() {
		try {
			service.mudarStatus(grupo);
			helper.addInfoMessage("Status alterado com sucesso!");
		} catch (Exception e) {
			helper.addErrorMessage("Erro ao alterar o status do grupo: " + "'" + grupo.getNome() + "'");
		}
	}

	public void permissoesPorGrupo() {
		grupo = service.findOneComPermissoes(id);
		FacesUtil.updateComponents("frm-dialogo");
		FacesUtil.executeJS("PF('dlg').show();");
	}

	public boolean isNovo() {
		return grupo.getId() == null;
	}

	public List<GrupoUsuario> getGrupos() {
		return grupos;
	}

	public GrupoUsuario getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoUsuario grupo) {
		this.grupo = grupo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEncodedId() {
		if (id == null)
			return "";
		return new AES().codificar(id.toString());
	}
}