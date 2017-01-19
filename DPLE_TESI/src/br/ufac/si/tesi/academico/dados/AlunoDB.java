package br.ufac.si.tesi.academico.dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.si.tesi.academico.modelo.Aluno;
import br.ufac.si.tesi.academico.modelo.Centro;

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
				//+ "'" + aluno.getPne() + "',"
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
				//+ "set pne='" + aluno.getNome() + "' "
				+ "set curso_codigo='" + aluno.getCurso() + "' "
				+ "where matricula='" + aluno.getMatricula() + "';";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean deletealuno(int matricula) {
		
		String sql = "delete from aluno where matricula=" + matricula + ";";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Aluno getAluno(int matricula) {

		Aluno aluno = null;
		Centro centro = null;
		String sql = "select * from aluno where matricula=" + matricula + ";";
		
		rs = conexao.consulte(sql);
		try {
			while (rs.next()) {
				aluno = new Aluno();
				aluno.setMatricula(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setRg(rs.getInt(3));
				aluno.setCpf(rs.getLong(4));
				aluno.setTelefone(rs.getString(5));
				aluno.setEndereco(rs.getString(6));
				aluno.setCep(rs.getString(7));
				aluno.setEmail(rs.getString(8));
				aluno.setPne(rs.getBoolean(9));
				aluno.setCentro(dadosCentro.getCentro(rs.getString(10)));				
			}
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return aluno;
		
	}
	
	public List<Aluno> getAlunos() {
		
		List<Aluno> lista = new ArrayList<Aluno>();
		Aluno aluno = null;
		String sql = "select * from aluno;";
		
		rs = conexao.consulte(sql);
		try {
			while (rs.next()) {
				aluno = new Aluno();
				aluno.setMatricula(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setRg(rs.getInt(3));
				aluno.setCpf(rs.getLong(4));
				aluno.setTelefone(rs.getString(5));
				aluno.setEndereco(rs.getString(6));
				aluno.setCep(rs.getString(7));
				aluno.setEmail(rs.getString(8));
				aluno.setSubstituto(rs.getBoolean(9));
				aluno.setCentro(dadosAlunos.getCentro(rs.getString(10)));
				lista.add(aluno);
			}
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return lista;
		
	}
	
	public List<Aluno> getAlunos(String nome) {
		
		List<Aluno> lista = new ArrayList<Aluno>();
		Aluno aluno = null;
		String sql = "select * from aluno where nome like '%" + nome + "%';";
		
		rs = conexao.consulte(sql);
		try {
			while (rs.next()) {
				aluno = new Aluno();
				aluno.setMatricula(rs.getInt(1));
				aluno.setNome(rs.getString(2));
				aluno.setRg(rs.getInt(3));
				aluno.setCpf(rs.getLong(4));
				aluno.setTelefone(rs.getString(5));
				aluno.setEndereco(rs.getString(6));
				aluno.setCep(rs.getString(7));
				aluno.setEmail(rs.getString(8));
				aluno.setSubstituto(rs.getBoolean(9));
				aluno.setCentro(dadosCentro.getCentro(rs.getString(10)));
				lista.add(aluno);
			}
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return lista;
		
	}
	
}