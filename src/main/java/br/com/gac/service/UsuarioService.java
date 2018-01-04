package br.com.gac.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.gac.dao.UsuarioDAO;
import br.com.gac.exception.RegistroExistenteException;
import br.com.gac.model.Usuario;
import br.com.gac.util.CpfUtil;
import br.com.gac.util.Criptografia;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -3776847062994423876L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private CpfUtil cpfUtil;

	@Transactional
	public void saveOrUpdate(Usuario usuario) {

		Usuario existente = usuarioDAO.findByMatricula(usuario.getMatricula());

		if (existente != null && !usuario.equals(existente))
			throw new RegistroExistenteException("Já existe um usuário cadastro com essa matrícula.");

		if (existente == null) {
			String CPF = cpfUtil.somenteNumeros(usuario.getCpf());
			usuario.setSenha(criptografar(CPF));
		}

		usuarioDAO.save(usuario);
	}

	@Transactional
	public void mudarStatus(Usuario usuario) {
		usuario.mudarStatus();
		usuarioDAO.save(usuario);
	}

	public Usuario findByMatricula(String matricula) {
		return usuarioDAO.findByMatricula(matricula);
	}

	private String criptografar(String senha) {
		return new Criptografia().criptografar(senha);
	}

}