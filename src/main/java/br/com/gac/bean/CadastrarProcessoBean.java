package br.com.gac.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;

import br.com.gac.bo.ProcessoBO;
import br.com.gac.constantes.Localidades;
import br.com.gac.constantes.Status;
import br.com.gac.constantes.Tipo;
import br.com.gac.infra.jsf.MessagesHelper;
import br.com.gac.model.Arquivo;
import br.com.gac.model.Processo;
import br.com.gac.model.Usuario;
import br.com.gac.security.Logado;

@Named
@ViewScoped
public class CadastrarProcessoBean implements Serializable {

	private static final long serialVersionUID = 8500794956236523219L;

	private boolean skip;
	private Processo processo = new Processo();
	private Arquivo arquivo;

	@Inject
	@Logado
	private Usuario usuarioLogado;

	@Inject
	private ProcessoBO processoBO;

	@Inject
	private MessagesHelper helper;

	@PostConstruct
	public void init() {
	}
	
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}

	}

	public String salvar() {
		try {
			processo.setUsuario(usuarioLogado);
			processo.setStatus(Status.ANALISE);
			processo.adicionaArquivo(arquivo);
			processoBO.save(processo);	
			helper.addInfoMessage("Processo salvo com sucesso!");
			return "/private/paginas/sucesso.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			helper.addErrorMessage(e.getMessage());
			return null;
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			arquivo = new Arquivo(Tipo.LIBELO);
			arquivo.setArquivo(event.getFile().getContents());
			arquivo.setNome(event.getFile().getFileName());
			arquivo.setExtensao(event.getFile().getContentType());	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Localidades[] getLocalidades() {
		return Localidades.values();
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

}
