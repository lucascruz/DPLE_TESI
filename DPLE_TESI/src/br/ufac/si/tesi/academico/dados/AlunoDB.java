package br.ufac.si.tesi.academico.dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.si.tesi.academico.modelo.Curso;
import br.ufac.si.tesi.academico.modelo.Aluno;

public class AlunoDB {
	
	private Conexao conexao;
	private ResultSet rs;
	private CursoDB dadosCurso;
	
	public AlunoDB(Conexao conexao) {
		
		this.conexao = conexao;
		dadosCurso = new CursoDB(conexao);
		
	}
	
	public AlunoDB() { }
	
	public void setConexao(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public boolean insertAluno(Aluno aluno) throws SQLException {
		
		String sql = "insert into aluno (matricula, nome, telefone, endereco, cep, email, sexo, pne, curso_codigo) "
				+ "values ( " + aluno.getMatricula() + ","
						+ "'" + aluno.getNome() + "',"
						+ "'" + aluno.getFone() + "',"
						+ "'" + aluno.getEndereco() + "',"
						+ "'" + aluno.getCep() + "',"
						+ "'" + aluno.getEmail() + "',"
						+ "'" + aluno.getSexo() + "',"
						      + aluno.isPne() + ","
			      		+ "'" + aluno.getCurso().getCodigo() + "')";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean updateProfessor(Aluno aluno) throws SQLException {
		
		String sql = "update aluno set "
				+ "nome = '" + aluno.getNome() + "',"
				+ "telefone = '" + aluno.getFone() + "',"
				+ "endereco = '" + aluno.getEndereco() + "',"
				+ "cep = '" + aluno.getCep() + "',"
				+ "email = '" + aluno.getEmail() + "',"
				+ "sexo = '" + aluno.getSexo() + "',"
				+ "pne = " + aluno.isPne() + ","
				+ "curso_codigo = '" + aluno.getCurso().getCodigo() + "' "
				+ "where matricula = " + aluno.getMatricula() + ";";
		
		System.out.println(sql);
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean deleteAluno(int matricula) throws SQLException {
		
		String sql = "delete from aluno where matricula=" + matricula + ";";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Aluno getAluno(int matricula) throws SQLException {

		Aluno aluno = null;
		Curso curso = null;
		String sql = "select * from aluno where matricula=" + matricula + ";";
		
		rs = conexao.consulte(sql);


			while (rs.next()) {
				aluno = new Aluno();
				aluno.setMatricula(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setFone(rs.getString(3));
				aluno.setEndereco(rs.getString(4));
				aluno.setCep(rs.getString(5));
				aluno.setEmail(rs.getString(6));
				aluno.setSexo(rs.getString(7));
				aluno.setPne(rs.getBoolean(8));
				aluno.setCurso(dadosCurso.getCurso(rs.getInt(9)));				
			}

		
		return aluno;
		
	}
	
	public List<Aluno> getAlunos() throws SQLException {
		
		List<Aluno> lista = new ArrayList<Aluno>();
		Aluno aluno = null;
		String sql = "select * from aluno;";
		
		rs = conexao.consulte(sql);
					while (rs.next()) {
				aluno = new Aluno();
				aluno = new Aluno();
				aluno.setMatricula(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setFone(rs.getString(3));
				aluno.setEndereco(rs.getString(4));
				aluno.setCep(rs.getString(5));
				aluno.setEmail(rs.getString(6));
				aluno.setSexo(rs.getString(7));
				aluno.setPne(rs.getBoolean(8));
				aluno.setCurso(dadosCurso.getCurso(rs.getInt(9)));	
				lista.add(aluno);
			}

		
		return lista;
		
	}
	
	public List<Aluno> getAlunos(String nome) throws SQLException {
		
		List<Aluno> lista = new ArrayList<Aluno>();
		Aluno aluno= null;
		String sql = "select * from aluno where nome like '%" + nome + "%';";
		
		rs = conexao.consulte(sql);
			while (rs.next()) {
				aluno = new Aluno();
				aluno.setMatricula(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setFone(rs.getString(3));
				aluno.setEndereco(rs.getString(4));
				aluno.setCep(rs.getString(5));
				aluno.setEmail(rs.getString(6));
				aluno.setSexo(rs.getString(7));
				aluno.setPne(rs.getBoolean(8));
				aluno.setCurso(dadosCurso.getCurso(rs.getInt(9)));	
				lista.add(aluno);
			}

		
		return lista;
		
	}

}
