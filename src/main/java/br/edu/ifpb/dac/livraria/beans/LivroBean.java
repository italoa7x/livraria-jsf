package br.edu.ifpb.dac.livraria.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.livraria.modelo.Autor;
import br.edu.ifpb.dac.livraria.modelo.Livro;
import br.edu.ifpb.dac.livraria.servico.ServicoAutores;
import br.edu.ifpb.dac.livraria.servico.ServicoLivros;

@Named
@ViewScoped
public class LivroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private List<Livro> livros;
	private Integer autorId;
	private List<Autor> autores;
	private List<Autor> autoresLivro;
	
	@EJB
	private ServicoLivros servicoLivro;
	@EJB
	private ServicoAutores servicoAutor;
	@Inject
	private FacesContext facesContext;
	
	public LivroBean() {
		
	}
	
	@PostConstruct
	public void init() {
		livros = this.servicoLivro.todosLivros();
		autores = this.servicoAutor.todosAutores();
		autoresLivro = new ArrayList<Autor>();
		System.out.println("[INFO] LivroBean criado ");
	}
	
	public void carregaAutoresLivro() {
		if (livro.getAutores()!=null && !livro.getAutores().isEmpty()) {
			autoresLivro = livro.getAutores();
			System.out.println("CarregaAutoresLivro: "+autoresLivro.size() +" - autores carregados.");
		}else autoresLivro = new ArrayList<Autor>();
	}
	
	public Livro getLivro() {
		return livro;
	}
	
		
	public String cadastra() {
		
		System.out.println("Cadastra - Livro: "+livro.getTitulo()+" - DATA: "+livro.getDataLancamento());
		livro.setAutores(autoresLivro);
		
		try {
		this.servicoLivro.salva(livro);
		
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para alterar.",null));
			FacesContext.getCurrentInstance()
		    .getExternalContext()
		    .getFlash().setKeepMessages(true);
			
			return null;
		}
		
		livros.add(livro);
		this.livro = new Livro();
		this.autoresLivro = new ArrayList<Autor>();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Livro gravado com sucesso.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);

		return "/admin/livros.xhtml?faces-redirect=true";
	}
	
	public String altera() {
		System.out.println("Altera - Livro: "+livro.getTitulo());
		livro.setAutores(autoresLivro);
		try {
			this.servicoLivro.altera(livro);			
		} catch (EJBAccessException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário sem permissão para alterar.",null));
			return null;
		}

		this.livro = new Livro();
		this.autoresLivro = new ArrayList<Autor>();
		
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Livro alterado com sucesso.",null));
		
		FacesContext.getCurrentInstance()
	    .getExternalContext()
	    .getFlash().setKeepMessages(true);
		
		
		return "livros?faces-redirect=true";
	}
	
	public void adicionaAutorLivro() {
		Autor autor = servicoAutor.buscaPelaId(autorId);
		System.out.println("AdicionaAutorLivro - Livro: "+livro.getTitulo()+" - Autor: "+autor.getNome());
		autoresLivro.add(autor);
	}
	
	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}


	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Autor> getAutoresLivro() {
		return autoresLivro;
	}

	public void setAutoresLivro(List<Autor> autoresLivro) {
		this.autoresLivro = autoresLivro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	

}
