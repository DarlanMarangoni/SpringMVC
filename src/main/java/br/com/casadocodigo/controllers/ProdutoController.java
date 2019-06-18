package br.com.casadocodigo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.daos.ProdutoDAO;
import br.com.casadocodigo.models.Produto;
import br.com.casadocodigo.models.enuns.TipoPreco;
import br.com.casadocodigo.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@InitBinder//permite que o spring chame o metodo automaticamente
	public void InitBinder(WebDataBinder webDataBinder) {
		//adiciona ProdutoValidation para o ProdutoController
		webDataBinder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	//BindingResult adicionado para que o spring possa buscar os erros de validacao
	//deve ser adicionado APOS o Objeto que sera validado
	public ModelAndView save(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return form();
		}
		
		produtoDAO.save(produto);
		//permite enviar parametros para metodo GET que duram apenas uma requisicao!!
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDAO.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}
}
