package br.ufac.si.tesi.academico.dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.si.tesi.academico.modelo.*;

public class CursoDB {
	
	private Conexao conexao;
	private ResultSet rs;
	private ProfessorDB dadosProfessor;
	
	public CursoDB(Conexao conexao) {
		
		this.conexao = conexao;
		dadosProfessor = new ProfessorDB(conexao);
		
	}
	
	public CursoDB() { }
	
	public void setConexao(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public boolean insertCurso(Curso curso) throws SQLException {
		
		String sql = "insert into curso (codigo, nome, coordenador) "
				+ "values ( " + curso.getCodigo() + ","
						+ "'" + curso.getNome() + "',"
			      		+ "'" + curso.getProfessor().getMatricula() + "')";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean updateCurso(Curso curso) throws SQLException {
		
		String sql = "update curso set "
				+ "nome = " + curso.getNome() + ","
				+ "coordenador = '" + curso.getProfessor().getMatricula()+ "' "
				+ "where codigo = " + curso.getCodigo() + ";";
				
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean deleteCurso(int codigo) throws SQLException {
		
		String sql = "delete from curso where codigo=" +codigo + ";";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Curso getCurso(int codigo) throws SQLException {

		Curso curso = null;
		String sql = "select * from curso where codigo=' " + codigo + "';";
		
		rs = conexao.consulte(sql);

			while (rs.next()) {
				curso = new Curso();
				curso.setCodigo(rs.getInt(1));
				curso.setNome(rs.getString(2));
				curso.setCoordenador(dadosProfessor.getProfessor(rs.getInt(3)));				
			}
		
		return curso;
		
	}
	
	public List<Curso> getCursos() throws SQLException {
		
		List<Curso> lista = new ArrayList<Curso>();
		Curso curso = null;
		String sql = "select * from curso;";
		
		rs = conexao.consulte(sql);

			while (rs.next()) {
				curso = new Curso();
				curso.setCodigo(rs.getInt(1));
				curso.setNome(rs.getString(2));
				curso.setCoordenador(dadosProfessor.getProfessor(rs.getInt(3)));	
				lista.add(curso);
			}
		
		return lista;
		
	}
	
	public List<Curso> getCursos(String nome)  throws SQLException{
		
		List<Curso> lista = new ArrayList<Curso>();
		Curso curso = null;
		String sql = "select * from curso where nome like '%" + nome + "%';";
		
		rs = conexao.consulte(sql);

			while (rs.next()) {
				curso = new Curso();
				curso.setCodigo(rs.getInt(1));
				curso.setNome(rs.getString(2));
				curso.setCoordenador(dadosProfessor.getProfessor(rs.getInt(3)));	
				lista.add(curso);
			}

		return lista;
		
	}

}
