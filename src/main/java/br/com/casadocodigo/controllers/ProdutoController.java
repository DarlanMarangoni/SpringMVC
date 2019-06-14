package br.com.casadocodigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.daos.ProdutoDAO;
import br.com.casadocodigo.models.Produto;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@RequestMapping("/produtos/form")
	public String form() {
		return "produtos/form";
	}
	
	@RequestMapping("/produtos")
	public String save(Produto produto) {
		System.out.println(produto);
		produtoDAO.save(produto);
		return "produtos/ok";
	}
}
