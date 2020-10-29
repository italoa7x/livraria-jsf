package br.edu.ifpb.dac.livraria.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.dac.livraria.modelo.Autor;

@Stateless
public class ServicoAutores {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] AutorDao foi criado.");
	}
	
	public void salva(Autor autor) {
	    System.out.println("[INFO] Salvando o Autor " + autor.getNome());
	    manager.persist(autor);
	    System.out.println("[INFO] Salvou o Autor " + autor.getNome());
	}
	
	public List<Autor> todosAutores() {
	    System.out.println("[INFO] Consultando todos os autores ");
		return manager.createQuery("select a from Autor a",Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
	    System.out.println("[INFO] Consultando Autor pelo id ");

		Autor autor = manager.find(Autor.class,autorId);
		
		return autor;
	}
	
}
