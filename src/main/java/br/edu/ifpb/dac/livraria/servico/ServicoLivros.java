package br.edu.ifpb.dac.livraria.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.dac.livraria.modelo.Livro;

/**
 * Session Bean implementation class ServicoLivros
 */
@Stateless
public class ServicoLivros {

	@PersistenceContext
	private EntityManager manager;

	@PostConstruct
	void aposCriacao() {
		System.out.println("[INFO] ServicoLivros foi criado.");
	}

	// FALTA - Incluir o papel adequado segundo as instruções do roteiro
	@RolesAllowed("ADMIN_BASICO")
	public void salva(Livro livro) {
		System.out.println("[INFO] Salvando o Livro " + livro.getTitulo());
		manager.persist(livro);
		System.out.println("[INFO] Salvou o Livro " + livro.getTitulo());
	}

	// FALTA - Incluir o papel adequado segundo as instruções do roteiro
	@PermitAll
	public List<Livro> todosLivros() {
		return manager.createQuery("select l from Livro l", Livro.class).getResultList();
	}

	// FALTA - Incluir o papel adequado segundo as instruções do roteiro
	@PermitAll
	public Livro buscaPelaId(Integer livroId) {
		Livro livro = manager.find(Livro.class, livroId);

		return livro;
	}

	// FALTA - Incluir o papel adequado segundo as instruções do roteiro
	@RolesAllowed("ADMIN_GERENTE")
	public void altera(Livro livro) {
		System.out.println("[INFO] Alterando o Livro " + livro.getTitulo());
		manager.merge(livro);
		System.out.println("[INFO] Livro " + livro.getTitulo() + " alterado com sucesso.");
	}
}
