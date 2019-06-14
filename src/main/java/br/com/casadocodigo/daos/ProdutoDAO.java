package br.com.casadocodigo.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.models.Produto;

//@Component -> é uma super classe do repository
@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Produto produto) {
		em.persist(produto);
	}

}
