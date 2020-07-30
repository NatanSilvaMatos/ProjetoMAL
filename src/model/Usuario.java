package model;

import java.util.ArrayList;
import java.util.Date;

import database.BD;

public class Usuario {
	private String nome;
	private String email;
	private String senha;
	private Date dataNascimento;
	private Date dataCadastro;
	private String genero;

	public Usuario() {
		super();
	}
	
	public void adicionarUsuario(String nome, String email, String senha, Date dataNascimento, Date dataCadastro, String genero) {
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setDataNascimento(dataNascimento);
		usuario.setDataCadastro(dataCadastro);
		usuario.setGenero(genero);
		BD banco = new BD();
		banco.adicionarUsuario(usuario);
	}
	
	public void removerUsuario(Usuario usuario) {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
