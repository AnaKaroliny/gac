package br.com.gac.service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.gac.dao.ConfiguracaoEmailDAO;
import br.com.gac.email.ConfiguracaoEmail;

public class ConfiguracaoEmailService implements Serializable {

	private static final long serialVersionUID = 504045890216191078L;

	@Inject
	private ConfiguracaoEmailDAO dao;

	public ConfiguracaoEmail getConfiguracao() {
		return dao.findConfiguracao();
	}

	@Transactional
	public void saveOrUpdate(ConfiguracaoEmail email) {
		dao.save(email);
	}
}