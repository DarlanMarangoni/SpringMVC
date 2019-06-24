package br.com.casadocodigo.daos;

import java.util.List;

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

	public List<Produto> listar() {
		return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
	}
	
	public Produto find (Integer id) {
		return em.createQuery("SELECT DISTINCT (p) FROM Produto p join fetch p.precos precos WHERE p.id = :id", Produto.class)
				.setParameter("id", id).getSingleResult();
	}

}
