package model.repository;

import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Jogador;

public class JogadorRepository implements BaseRepository<Jogador> {

	public Jogador salvar(Jogador novoJogador) {
		String sql = "INSERT INTO jogador(nome, login, data_nascimento) VALUES(?,?,?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, sql);
		try {
			pstmt.setString(1, novoJogador.getNome());
			pstmt.setString(2, novoJogador.getEmail());
			pstmt.setDate(3, Date.valueOf(novoJogador.getDataNascimento()));
			
			pstmt.execute();
			ResultSet result = pstmt.getGeneratedKeys();
			
			if(result.next()) {
				novoJogador.setId(result.getInt("id"));
			}			
			
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar novo jogador!");
			System.out.println(erro.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return novoJogador;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String sql = "DELETE FROM jogador WHERE id = " + id;
		try {
			if(stmt.executeUpdate(sql) == 1) {
				retorno = true;
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return retorno;
	}

	@Override
	public boolean alterar(Jogador jogador) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String sql = "UPDATE jogador SET nome = " + jogador.getNome()
					+ ", login = " + jogador.getEmail()
					+ ", data_nascimento = " + jogador.getDataNascimento()
					+ " WHERE id = " + jogador.getId();
		
		try {
			if(stmt.executeUpdate(sql) == 1){
				retorno = true;
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return retorno;
	}

	@Override
	public Jogador consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;

		Jogador jogador = new Jogador();
		String sql = "SELECT * FROM jogador WHERE id = " + id;
		
		try {
			result = stmt.executeQuery(sql);
			if(result.next()) {
				jogador.setId(Integer.parseInt(result.getString("id")));
				jogador.setNome(result.getString("nome"));
				jogador.setEmail(result.getString("login"));
				jogador.setDataNascimento(LocalDate.parse(result.getString("data_nascimento"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				
			}
			
		} catch (SQLException erro) {
			System.out.println("Erro: " + erro.getMessage());
		}
		
		
		return jogador;
	}

	@Override
	public ArrayList<Jogador> consultarTodos() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet result = null;
		
		ArrayList<Jogador> listaJogadores = new ArrayList<>();
		String sql = "SELECT * FROM jogador";
		
		try {
			Jogador jogador = new Jogador();
			
			jogador.setId(Integer.parseInt(result.getString("id")));
			jogador.setNome(result.getString("nome"));
			jogador.setEmail(result.getString("login"));
			jogador.setDataNascimento(LocalDate.parse(result.getString("data_nascimento"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			
			listaJogadores.add(jogador);
			
		} catch (SQLException erro) {
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return listaJogadores;
	}
	
}
