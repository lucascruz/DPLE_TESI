package br.ufac.si.tesi.academico.dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.si.tesi.academico.modelo.*;

public class ProfessorDB {
	
	private Conexao conexao;
	private ResultSet rs;
	private CentroDB dadosCentro;
	
	public ProfessorDB(Conexao conexao) {
		
		this.conexao = conexao;
		dadosCentro = new CentroDB(conexao);
		
	}
	
	public ProfessorDB() { }
	
	public void setConexao(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public boolean insertProfessor(Professor professor) {
		
		String sql = "insert into professor (matricula, nome, rg, cpf, telefone, endereco, cep, email, substituto, centro_sigla) "
				+ "values ( " + professor.getMatricula() + ","
						+ "'" + professor.getNome() + "',"
						      + professor.getRg() + ","
						      + professor.getCpf() + ","
						+ "'" + professor.getTelefone() + "',"
						+ "'" + professor.getEndereco() + "',"
						+ "'" + professor.getCep() + "',"
						+ "'" + professor.getEmail() + "',"
						      + professor.isSubstituto() + ","
			      		+ "'" + professor.getCentro().getSigla() + "')";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean updateProfessor(Professor professor) {
		
		String sql = "update professor set "
				+ "nome = '" + professor.getNome() + "',"
				+ "rg = " + professor.getRg() + ","
				+ "cpf = " + professor.getCpf() + ","
				+ "telefone = '" + professor.getTelefone() + "',"
				+ "endereco = '" + professor.getEndereco() + "',"
				+ "cep = '" + professor.getCep() + "',"
				+ "email = '" + professor.getEmail() + "',"
				+ "substituto = " + professor.isSubstituto() + ","
				+ "centro_sigla = '" + professor.getCentro().getSigla() + "' "
				+ "where matricula = " + professor.getMatricula() + ";";
				
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean deleteProfessor(int matricula) {
		
		String sql = "delete from professor where matricula=" + matricula + ";";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Professor getProfessor(int matricula)  throws SQLException{

		Professor professor = null;
		String sql = "select * from professor where matricula=" + matricula + ";";
		
		rs = conexao.consulte(sql);

			while (rs.next()) {
				professor = new Professor();
				professor.setMatricula(rs.getInt(1));
				professor.setNome(rs.getString(2));
				professor.setRg(rs.getInt(3));
				professor.setCpf(rs.getLong(4));
				professor.setTelefone(rs.getString(5));
				professor.setEndereco(rs.getString(6));
				professor.setCep(rs.getString(7));
				professor.setEmail(rs.getString(8));
				professor.setSubstituto(rs.getBoolean(9));
				professor.setCentro(dadosCentro.getCentro(rs.getString(10)));				
			}

		
		return professor;
		
	}
	
	public List<Professor> getProfessores()  throws SQLException{
		
		List<Professor> lista = new ArrayList<Professor>();
		Professor professor = null;
		String sql = "select * from professor;";
		
		rs = conexao.consulte(sql);

			while (rs.next()) {
				professor = new Professor();
				professor.setMatricula(rs.getInt(1));
				professor.setNome(rs.getString(2));
				professor.setRg(rs.getInt(3));
				professor.setCpf(rs.getLong(4));
				professor.setTelefone(rs.getString(5));
				professor.setEndereco(rs.getString(6));
				professor.setCep(rs.getString(7));
				professor.setEmail(rs.getString(8));
				professor.setSubstituto(rs.getBoolean(9));
				professor.setCentro(dadosCentro.getCentro(rs.getString(10)));
				lista.add(professor);
			}
		
		return lista;
		
	}
	
	public List<Professor> getProfessores(String nome)  throws SQLException{
		
		List<Professor> lista = new ArrayList<Professor>();
		Professor professor = null;
		String sql = "select * from professor where nome like '%" + nome + "%';";
		
		rs = conexao.consulte(sql);

			while (rs.next()) {
				professor = new Professor();
				professor.setMatricula(rs.getInt(1));
				professor.setNome(rs.getString(2));
				professor.setRg(rs.getInt(3));
				professor.setCpf(rs.getLong(4));
				professor.setTelefone(rs.getString(5));
				professor.setEndereco(rs.getString(6));
				professor.setCep(rs.getString(7));
				professor.setEmail(rs.getString(8));
				professor.setSubstituto(rs.getBoolean(9));
				professor.setCentro(dadosCentro.getCentro(rs.getString(10)));
				lista.add(professor);
			}

		return lista;
		
	}

}
