package br.com.gac.dao.datamodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.gac.bo.ArquivoBO;
import br.com.gac.dao.filter.ArquivoFilter;
import br.com.gac.model.Arquivo;

public class ArquivoDataModel extends LazyDataModel<Arquivo> implements Serializable{

	private static final long serialVersionUID = -8369265724747577009L;
	
	private ArquivoFilter filter;

	private ArquivoBO arquivoBO;

	List<Arquivo> arquivos = null;

	public ArquivoDataModel(ArquivoFilter filter, ArquivoBO arquivoBO) {
		this.filter = filter;
		this.arquivoBO = arquivoBO;
	}

	@Override
	public List<Arquivo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		arquivos = arquivoBO.getArquivoByProcessoId(first, pageSize, filter);
		setRowCount(arquivoBO.encontrarQuantidadeArquivo(filter).intValue());
		return arquivos;
	}
	
	@Override
	public Object getRowKey(Arquivo arquivo) {
		return arquivo.getId();
	}

	@Override
	public Arquivo getRowData(String rowKey) {
		Long longRowKey = Long.parseLong(rowKey);
		for (Arquivo me : arquivos) {
			if (me.getId().equals(longRowKey)) {
				return me;
			}
		}
		return null;
	}


}
