package br.ufac.si.tesi.academico.dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.si.tesi.academico.modelo.Disciplina;

public class DisciplinaDB {
	
	private Conexao conexao;
	private ResultSet rs;
	private CentroDB dadosCentro;
	
	public DisciplinaDB(Conexao conexao) {
		
		this.conexao = conexao;
		dadosCentro = new CentroDB(conexao);
		
	}
	
	public DisciplinaDB() { }
	
	public void setConexao(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public boolean insertDisciplina(Disciplina disciplina) {
		
		String sql = "insert into disciplina (codigo, nome, ch,  centro_sigla) "
				+ "values ( " + disciplina.getCodigo() + ","
						+ "'" + disciplina.getNome() + "',"
						      + disciplina.getCh() + ","
			      		+ "'" + disciplina.getCentro().getSigla() + "')";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean updateDisciplina(Disciplina disciplina) {
		
		String sql = "update disciplina set "
				+ "nome = " + disciplina.getNome() + ","
				+ "ch= " + disciplina.getCh() + ","
				+ "centro_sigla = '" + disciplina.getCentro().getSigla() + "' "
				+ "where codigo = " + disciplina.getCodigo() + ";";
				
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean deleteDisciplina(int codigo) {
		
		String sql = "delete from disciplina where codigo=" +codigo + ";";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Disciplina getDisciplina(int codigo) throws SQLException {

		Disciplina disciplina = null;
		String sql = "select * from disciplina where codigo=" + codigo + ";";
		
		rs = conexao.consulte(sql);

			while (rs.next()) {
				disciplina = new Disciplina();
				disciplina.setCodigo(rs.getString(1));
				disciplina.setNome(rs.getString(2));
				disciplina.setCh(rs.getInt(3));
				disciplina.setCentro(dadosCentro.getCentro(rs.getString(4)));				
			}
		
		return disciplina;
		
	}
	
	public List<Disciplina> getDisciplinas() throws SQLException {
		
		List<Disciplina> lista = new ArrayList<Disciplina>();
		Disciplina disciplina = null;
		String sql = "select * from disciplina;";
		
		rs = conexao.consulte(sql);

			while (rs.next()) {
				disciplina = new Disciplina();
				disciplina.setCodigo(rs.getString(1));
				disciplina.setNome(rs.getString(2));
				disciplina.setCh(rs.getInt(3));
				disciplina.setCentro(dadosCentro.getCentro(rs.getString(4)));		
				lista.add(disciplina);
			}
		
		return lista;
		
	}
	
	public List<Disciplina> getDisciplinas(String nome)  throws SQLException{
		
		List<Disciplina> lista = new ArrayList<Disciplina>();
		Disciplina disciplina = null;
		String sql = "select * from disciplina where nome like '%" + nome + "%';";
		
		rs = conexao.consulte(sql);

			while (rs.next()) {
				disciplina = new Disciplina();
				disciplina.setCodigo(rs.getString(1));
				disciplina.setNome(rs.getString(2));
				disciplina.setCh(rs.getInt(3));
				disciplina.setCentro(dadosCentro.getCentro(rs.getString(4)));		
				lista.add(disciplina);
			}

		return lista;
		
	}

}
