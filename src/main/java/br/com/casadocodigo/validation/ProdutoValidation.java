package br.com.casadocodigo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.models.Produto;

public class ProdutoValidation implements Validator{

	//verifica se a classe recebida como parametro e mesmo um Produto
	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//metodo que rejeita o campo se estiver vazio
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		
		Produto produto = (Produto) target;
		
		//pois paginas e um tipo primitivo e não pode ser null
		if (produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required");
		}
	}

}
