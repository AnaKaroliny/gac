package br.com.gac.service.validation;

public interface Validator<T> {

	void validar(T t);
	
}