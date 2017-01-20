package br.ufac.si.tesi.academico.controle;

import java.sql.SQLException;
import java.util.List;

import br.ufac.si.tesi.academico.dados.CentroDB;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Centro;

public class CentroControle {
	
	private CentroDB dados = null;
	
	public CentroControle(Conexao conexao) {

		dados = new CentroDB(conexao);
		
	}
	
	public boolean insertCentro(String sigla, String nome) {
		
		Centro centro = null;
		
		if (sigla.isEmpty() || nome.isEmpty()) {
			return false;
		} else {
			centro = new Centro();
			centro.setSigla(sigla);
			centro.setNome(nome);
			return dados.insertCentro(centro);
		}
		
	}
	
	public boolean updateCentro(String sigla, String nome) throws SQLException {
		
		Centro centro = null;
		
		if (sigla.isEmpty() || nome.isEmpty()) {
			return false;
		} else {
			centro = dados.getCentro(sigla);
			centro.setNome(nome);
			return dados.updateCentro(centro);
		}
		
	}
	
	public boolean deleteCentro(String sigla) {
		
		return dados.deleteCentro(sigla);		
		
	}
	
	public List<Centro> getCentros(String nome) throws SQLException {
		
		return dados.getCentros(nome);
		
	}
	
	public Centro getCentro(String sigla) throws SQLException {
		
		return dados.getCentro(sigla);
		
	}
	
	public List<Centro> getCentros() throws SQLException {
		
		return dados.getCentros();
		
	}

}
