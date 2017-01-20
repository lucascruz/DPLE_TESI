package br.ufac.si.tesi.academico.dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.si.tesi.academico.modelo.Aluno;

public class AlunoDB {
	
	private Conexao conexao;
	private ResultSet rs;
	private CursoDB dadosCurso;

	
	public AlunoDB(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public AlunoDB() { }
	
	public void setConexao(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public boolean insertAluno(Aluno aluno) {
		
		String sql = "insert into aluno (matricula, nome, telefone, endereco, cep, sexo, pne, curso_codigo) "
				+ "values ( " + aluno.getMatricula() + ","
				+ "'" + aluno.getNome() + "',"
				      + aluno.getFone() + ","
				      + aluno.getEndereco() + ","
				+ "'" + aluno.getCep() + "',"
				+ "'" + aluno.getSexo() + "',"
				+ "'" + aluno.isPne() + "',"
				+ "'" + aluno.getCurso() + "')";
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean updateAluno(Aluno aluno) {
		
		String sql = "update aluno "
				+ "set nome='" + aluno.getNome() + "' "
				+ "set telefone='" + aluno.getFone() + "' "
				+ "set endereco='" + aluno.getEndereco() + "' "
				+ "set cep='" + aluno.getCep() + "' "
				+ "set sexo='" + aluno.getSexo() + "' "
				+ "set pne='" + aluno.isPne() + "' "
				+ "set curso_codigo='" + aluno.getCurso() + "' "
				+ "where matricula='" + aluno.getMatricula() + "';";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean deleteAluno(int matricula) {
		
		String sql = "delete from aluno where matricula=" + matricula + ";";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Aluno getAluno(int matricula) {

		Aluno aluno = null;
		String sql = "select * from aluno where matricula=" + matricula + ";";
		
		rs = conexao.consulte(sql);
		try {
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
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
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
		Aluno aluno = null;
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