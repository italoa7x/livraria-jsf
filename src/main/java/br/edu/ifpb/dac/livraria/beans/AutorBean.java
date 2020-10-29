package br.edu.ifpb.dac.livraria.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.livraria.modelo.Autor;
import br.edu.ifpb.dac.livraria.servico.ServicoAutores;

@Named
@RequestScoped
public class AutorBean{

	private Autor autor = new Autor();
	private List<Autor> autores;
	@EJB
	private ServicoAutores dao;
	
	public Autor getAutor() {
		return autor;
	}
	
	public AutorBean() {
		
	}
	
	@PostConstruct
	public void init() {
		autores = this.dao.todosAutores();
	}
	
	public void cadastra() {
		System.out.println("Cadastra - Autor: "+autor.getNome());
		this.dao.salva(autor);
		
		autores.add(autor);
		this.autor = new Autor();
	}
	
	public List<Autor> getAutores() {
		return autores;
	}
}
