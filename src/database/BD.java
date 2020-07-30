package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import model.Anime;
import model.Manga;
import model.Usuario;

public class BD {
	private Connection conexao = null;
	private PreparedStatement statement = null;
	private ResultSet rs = null;

	public void conectar() {
		if(conexao == null) {
			try {
				conexao = DriverManager.getConnection("jdbc:mysql://localhost/projetomal?useTimezone=true&serverTimezone=UTC","root","root");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void adicionarAnime(Anime anime) {
		conectar();
		String sqlcommand = "insert into anime " + "(nome, status, episodios, estudio) " + " values(?,?,?,?)";
		try {
			statement = conexao.prepareStatement(sqlcommand);
			statement.setString(1, anime.getNome());
			statement.setString(2, anime.getStatus());
			statement.setInt(3, anime.getEpisodios());
			statement.setString(4, anime.getEstudio());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	public void adicionarManga(Manga manga) {
		conectar();
		String sqlcommand = "insert into manga " + "(nome, status, capitulos, autor) " + " values(?,?,?,?)";
		try {
			statement = conexao.prepareStatement(sqlcommand);
			statement.setString(1, manga.getNome());
			statement.setString(2, manga.getStatus());
			statement.setInt(3, manga.getCapitulos());
			statement.setString(4, manga.getAutor());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	public void adicionarUsuario(Usuario usuario) {
		conectar();
		String sqlcommand = "insert into usuario " + "(nome, email, senha, dataDeNascimento, dataCadastro, genero, tipo) " + " values(?,?,?,?,?,?,?)";
		try {
			statement = conexao.prepareStatement(sqlcommand);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getEmail());
			statement.setString(3, usuario.getSenha());
			statement.setDate(4, new java.sql.Date(usuario.getDataNascimento().getTime()));
			statement.setDate(5, new java.sql.Date(usuario.getDataCadastro().getTime()));
			statement.setString(6, usuario.getGenero());
			statement.setString(7, "Usuario");
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	public boolean verificaLogin(String email, String senha) {
		conectar();
		String sqlcommand = "select email, senha from usuario where email = ? and senha = ?";
		StringBuffer textoEmail = new StringBuffer();
		StringBuffer textoSenha = new StringBuffer();
		try {
			statement = conexao.prepareStatement(sqlcommand);
			statement.setString(1, email);
			statement.setString(2, senha);
			rs = statement.executeQuery();
			while(rs.next()) { 
				textoEmail.append(rs.getString("email"));
				textoSenha.append(rs.getString("senha"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		if(email.toString().equalsIgnoreCase(textoEmail.toString()) && senha.toString().equalsIgnoreCase(textoSenha.toString())) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean validaApelido(String nome) {
		conectar();
		String sqlcommand = "select * from usuario where nome = ?";
		StringBuffer textoNome = new StringBuffer();
		try {
			statement = conexao.prepareStatement(sqlcommand);
			statement.setString(1, nome);
			rs = statement.executeQuery();
			while(rs.next()) {
				textoNome.append(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		if(textoNome.toString().equals(nome)) {
			return true;
		}
		else {
			return false;
		}		
	}

	public String[] preencheDadosUsuario(String email) {
		conectar();
		String sqlcommand = "select * from usuario where email = ?";
		StringBuffer texto = new StringBuffer();
		try {
			statement = conexao.prepareStatement(sqlcommand);
			statement.setString(1, email);
			rs = statement.executeQuery();
			while(rs.next()) {
				texto.append(rs.getString("nome") + "\n" + rs.getDate("dataDeNascimento") + "\n" + rs.getString("dataCadastro") + "\n" + rs.getString("genero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String conteudoUsuario[] = new String[4];
		for(int i = 0;i <= conteudoUsuario.length - 1;i++) {
			String[] split = texto.toString().split("\n");
			conteudoUsuario[i] = split[i];
		}
		return conteudoUsuario;		
	}

	public String[] retornaDadosTabela(String tabela, String numero, String dados) {
		conectar();
		int contador = 0;
		String sqlcommand = "select nome," + numero + "," + dados + " from " + tabela;
		StringBuffer texto = new StringBuffer();
		try {
			statement = conexao.prepareStatement(sqlcommand);
			rs = statement.executeQuery();
			while(rs.next()) {
				texto.append(rs.getString("nome") + "               " + rs.getString(numero) + "               " + rs.getString(dados) + "\n");
				contador++; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		String[] dadosTabela = new String[contador];
		for(int i = 0;i < dadosTabela.length;i++) {
			String [] split = texto.toString().split("\n");
			dadosTabela[i] = split[i];
		}
		return dadosTabela;
	}

	public void adicionaListaUsuario(String tabela, String nomeUsuario, String nome, int numero, int nota) {
		conectar();
		if(tabela.equals("anime")) {
			String sqlcommand = "insert into listaAnime (nomeUsuario, anime, episodios, nota) " + " values(?,?,?,?)";
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setString(1, nomeUsuario);
				statement.setString(2, nome);
				statement.setInt(3, numero);
				statement.setInt(4, nota);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			String sqlcommand = "insert into listaManga (nomeUsuario, manga, capitulos, nota) " + " values(?,?,?,?)";
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setString(1, nomeUsuario);
				statement.setString(2, nome);
				statement.setInt(3, numero);
				statement.setInt(4, nota);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}		
		}	
	}

	public boolean consultaListaUsuario(String tabela, String nomeUsuario, String obra) {
		conectar();
		StringBuffer texto = new StringBuffer();
		if(tabela.equals("anime")) {
			String sqlcommand = "select * from listaAnime where nomeUsuario = ? and anime = ?";
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setString(1, nomeUsuario);
				statement.setString(2, obra);
				rs = statement.executeQuery();
				while(rs.next()) {
					texto.append(rs.getString("nomeUsuario") + "  " + rs.getString("anime"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(texto.toString().isEmpty()) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			String sqlcommand = "select * from listaManga where nomeUsuario = ? and manga = ?";			
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setString(1, nomeUsuario);
				statement.setString(2, obra);
				rs = statement.executeQuery();				
				while(rs.next()) {
					texto.append(rs.getString("nomeUsuario") + "  " + rs.getString("manga"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(texto.toString().isEmpty()) {
				return false;				
			}
			else {
				return true;
			}
		}
	}

	public String[] retornaListaUsuario(String tabela, String nomeUsuario) {
		conectar();
		int contador = 0;
		StringBuffer texto = new StringBuffer();
		if(tabela.equals("anime")) {
			String sqlcommand = "select * from listaAnime where nomeUsuario = ?";
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setString(1, nomeUsuario);
				rs = statement.executeQuery();
				while(rs.next()) {
					texto.append(rs.getString("anime") + "                  " + rs.getString("nota") + "                  "  + rs.getString("episodios") + "\n");
					contador++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					statement.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}		
			String[] dadosTabela = new String[contador];
			for(int i = 0;i < dadosTabela.length;i++) {
				String [] split = texto.toString().split("\n");
				dadosTabela[i] = split[i];
			}
			return dadosTabela;
		}
		else {
			String sqlcommand = "select * from listaManga where nomeUsuario = ?";
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setString(1, nomeUsuario);
				rs = statement.executeQuery();
				while(rs.next()) {
					texto.append(rs.getString("manga") + "                  " + rs.getString("nota") + "                  "  + rs.getString("capitulos") + "\n");
					contador++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					statement.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
			String[] dadosTabela = new String[contador];
			for(int i = 0;i < dadosTabela.length;i++) {
				String [] split = texto.toString().split("\n");
				dadosTabela[i] = split[i];
			}
			return dadosTabela;			
		}
	}

	public boolean deletaObraLista(String tabela, String nomeUsuario, String obra) {
		conectar();
		int rowsAffected = 0;
		if(tabela.equals("anime")) {
			String sqlcommand = "delete from listaAnime where nomeUsuario = ? and anime = ?";
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setString(1, nomeUsuario);
				statement.setString(2, obra);
				rowsAffected = statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(rowsAffected > 0) {
				return true;
			}
			else {
				return false;
			}
		}	
		else {
			String sqlcommand = "delete from listaManga where nomeUsuario = ? and manga = ?";
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setString(1, nomeUsuario);
				statement.setString(2, obra);
				rowsAffected = statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(rowsAffected > 0) {
				return true;
			}
			else {
				return false;
			}		
		}
	}

	public boolean atualizaNota(String tabela, int nota, String nomeUsuario, String obra) {
		conectar();
		int rowsAffected = 0;
		if(tabela.equals("anime")) {
			String sqlcommand = "update listaAnime set nota = ? where nomeUsuario = ? and anime = ?";
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setInt(1, nota);
				statement.setString(2, nomeUsuario);
				statement.setString(3, obra);
				rowsAffected = statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(rowsAffected > 0) {
				return true;
			}
			else {
				return false;
			}			
		}
		else {
			String sqlcommand = "update listaManga set nota = ? where nomeUsuario = ? and manga = ?";
			try {
				statement = conexao.prepareStatement(sqlcommand);
				statement.setInt(1, nota);
				statement.setString(2, nomeUsuario);
				statement.setString(3, obra);
				rowsAffected = statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(rowsAffected > 0) {
				return true;
			}
			else {
				return false;
			}			
		}
	}
	
	public String retornaTipoUsuario(String email, String senha) {
		conectar();
		StringBuffer texto = new StringBuffer();
		String sqlcommand = "select * from usuario where email = ? and senha = ?";		
		try {
			statement = conexao.prepareStatement(sqlcommand);
			statement.setString(1, email);
			statement.setString(2, senha);
			rs = statement.executeQuery();
			while(rs.next()) {
				texto.append(rs.getString("tipo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}	
		return texto.toString();
	}
}


