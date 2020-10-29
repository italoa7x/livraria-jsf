package br.edu.ifpb.dac.livraria.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.livraria.modelo.Autor;
import br.edu.ifpb.dac.livraria.modelo.Usuario;
import br.edu.ifpb.dac.livraria.servico.ServicoAutores;
import br.edu.ifpb.dac.livraria.servico.ServicoUsuarios;
import java.io.Serializable;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	private String papel;
	private List<String> papeisUsuario;
	
	@EJB
	private ServicoUsuarios dao;
	
	
	public UsuarioBean() {
		
	}
	
	@PostConstruct
	public void init() {
		usuarios = this.dao.todosUsuarios();
		papeisUsuario = new ArrayList<String>();
	}
	
	public void cadastra() {
		System.out.println("Cadastra - Usuario: "+usuario.getNome());
		usuario.setPapeis(papeisUsuario);
		
		this.dao.salva(usuario);
		
		usuarios.add(usuario);
		this.usuario = new Usuario();
		
		papeisUsuario = new ArrayList<String>();
	}
	
	public void adicionaPapel() {
		papeisUsuario.add(papel);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public List<String> getPapeisUsuario() {
		return papeisUsuario;
	}

	public void setPapeisUsuario(List<String> papeisUsuario) {
		this.papeisUsuario = papeisUsuario;
	}
	
}
