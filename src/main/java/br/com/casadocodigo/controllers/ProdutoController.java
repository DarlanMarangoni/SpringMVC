package br.com.casadocodigo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.models.Produto;

@Controller
public class ProdutoController {

	@RequestMapping("/produtos/form")
	public String form() {
		return "produtos/form";
	}
	
	@RequestMapping("produtos")
	public String save(Produto produto) {
		System.out.println(produto);
		return "produtos/ok";
	}
}
