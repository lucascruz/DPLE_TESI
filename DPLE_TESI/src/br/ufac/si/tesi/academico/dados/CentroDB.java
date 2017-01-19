package br.ufac.si.tesi.academico.dados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.si.tesi.academico.modelo.Centro;

public class CentroDB {
	
	private Conexao conexao;
	private ResultSet rs;
	
	public CentroDB(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public CentroDB() { }
	
	public void setConexao(Conexao conexao) {
		
		this.conexao = conexao;
		
	}
	
	public boolean insertCentro(Centro centro) {
		
		String sql = "insert into centro (sigla, nome) values ('" + centro.getSigla() + "', '" + centro.getNome() + "')";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean updateCentro(Centro centro) {
		
		String sql = "update centro "
				+ "set nome='" + centro.getNome() + "' "
				+ "where sigla='" + centro.getSigla() + "';";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public boolean deleteCentro(String sigla) {
		
		String sql = "delete from centro where sigla='" + sigla + "'";
		
		int status = conexao.atualize(sql);
		
		if (status > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Centro getCentro(String sigla) {

		Centro centro = null;
		String sql = "select sigla, nome from centro where sigla='" + sigla + "';";
		
		rs = conexao.consulte(sql);
		try {
			while (rs.next()) {
				centro = new Centro();
				centro.setSigla(rs.getString(1));
				centro.setNome(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return centro;
		
	}
	
	public List<Centro> getCentros() {
		
		List<Centro> lista = new ArrayList<Centro>();
		Centro centro = null;
		String sql = "select sigla, nome from centro;";
		
		rs = conexao.consulte(sql);
		try {
			while (rs.next()) {
				centro = new Centro();
				centro.setSigla(rs.getString(1));
				centro.setNome(rs.getString(2));
				lista.add(centro);
			}
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return lista;
		
	}
	
	public List<Centro> getCentros(String nome) {
		
		List<Centro> lista = new ArrayList<Centro>();
		Centro centro = null;
		String sql = "select sigla, nome from centro where nome like '%" + nome + "%';";
		
		rs = conexao.consulte(sql);
		try {
			while (rs.next()) {
				centro = new Centro();
				centro.setSigla(rs.getString(1));
				centro.setNome(rs.getString(2));
				lista.add(centro);
			}
		} catch (SQLException e) {
			System.out.println("Erro: #" + e.getErrorCode() + " - " + e.getMessage());
		}
		
		return lista;
		
	}

}
