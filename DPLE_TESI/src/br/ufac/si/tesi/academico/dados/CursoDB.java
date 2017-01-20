package br.ufac.si.tesi.academico.dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.si.tesi.academico.modelo.Curso;

public class CursoDB {
	
	private Conexao conexao;
	private ResultSet rs;
	
	public CursoDB(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public CursoDB() { }
	
	public void setConexao(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public boolean insertCurso(Curso curso) {
		
		String sql = "insert into curso (codigo, nome) values ('" + curso.getCodigo() + "', '" + curso.getNome() + "')";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean updateCurso(Curso curso) {
		
		String sql = "update curso "
				+ "set nome='" + curso.getNome() + "' "
				+ "where codigo='" + curso.getCodigo() + "';";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean deleteCurso(int codigo) {
		
		String sql = "delete from curso where codigo='" + codigo + "'";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Curso getCurso(int codigo) {

		Curso curso = null;
		String sql = "select codigo, nome from curso where codigo='" + codigo + "';";
		
		rs = conexao.consulte(sql);
		try {
			while (rs.next()) {
				curso = new Curso();
				curso.setCodigo(rs.getInt(1));
				curso.setNome(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return curso;
		
	}
	
	public List<Curso> getCursos() {
		
		List<Curso> lista = new ArrayList<Curso>();
		Curso curso = null;
		String sql = "select codigo, nome from curso;";
		
		rs = conexao.consulte(sql);
		try {
			while (rs.next()) {
				curso = new Curso();
				curso.setCodigo(rs.getInt(1));
				curso.setNome(rs.getString(2));
				lista.add(curso);
			}
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return lista;
		
	}
	
	public List<Curso> getCursos(String nome) {
		
		List<Curso> lista = new ArrayList<Curso>();
		Curso curso = null;
		String sql = "select codigo, nome from curso where nome like '%" + nome + "%';";
		
		rs = conexao.consulte(sql);
		try {
			while (rs.next()) {
				curso = new Curso();
				curso.setCodigo(rs.getInt(1));
				curso.setNome(rs.getString(2));
				lista.add(curso);
			}
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return lista;
		
	}

}
