package model;

import database.BD;

public class Anime {
	private String nome;
	private String status; //exibindo, terminado, etc
	private int episodios;
	private String estudio;
	
	public void adicionarAnime(String nome, String status, int episodios, String estudio) {
		Anime anime = new Anime();
		anime.setNome(nome);
		anime.setStatus(status);
		anime.setEpisodios(episodios);
		anime.setEstudio(estudio);
		BD banco = new BD();
		banco.adicionarAnime(anime);
	}
	
	public void removerAnime(Anime anime) {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEpisodios() {
		return episodios;
	}

	public void setEpisodios(int episodios) {
		this.episodios = episodios;
	}

	public String getEstudio() {
		return estudio;
	}

	public void setEstudio(String estudio) {
		this.estudio = estudio;
	}
}
