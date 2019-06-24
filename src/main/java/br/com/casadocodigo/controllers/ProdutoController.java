package br.com.casadocodigo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.daos.ProdutoDAO;
import br.com.casadocodigo.infra.FileSaver;
import br.com.casadocodigo.models.Produto;
import br.com.casadocodigo.models.enuns.TipoPreco;
import br.com.casadocodigo.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder//permite que o spring chame o metodo automaticamente
	public void InitBinder(WebDataBinder webDataBinder) {
		//adiciona ProdutoValidation para o ProdutoController
		webDataBinder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {//recebe o produto na requisicao
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	//BindingResult adicionado para que o spring possa buscar os erros de validacao
	//deve ser adicionado APOS o Objeto que sera validado
	public ModelAndView save(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return form(produto);
		}
		
		String path = fileSaver.write("arquivo-sumario", sumario);
		produto.setSumarioPath(path);
		
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
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDAO.find(id);
		modelAndView.addObject("produto", produto);		
		return modelAndView;
	}
}
