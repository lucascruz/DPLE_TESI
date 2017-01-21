package br.ufac.si.tesi.academico.controle;

import java.sql.SQLException;
import java.util.List;

import br.ufac.si.tesi.academico.dados.DisciplinaDB;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Disciplina;

public class DisciplinaControle {
	
	private DisciplinaDB dados = null;
	private CentroControle centroControle = null;
	
	public DisciplinaControle(Conexao conexao) {

		dados = new DisciplinaDB(conexao);
		centroControle = new CentroControle(conexao);
		
	}
	
	public boolean insertDisciplina( int codigo, String nome, int ch, String centro_sigla) throws SQLException {
		
		Disciplina disciplina = null;
		
		if (codigo==0 || nome.isEmpty() || ch == 0 || centro_sigla.isEmpty()) {
			return false;
		} else {
			disciplina = new Disciplina();
			disciplina.setCodigo(codigo);
			disciplina.setNome(nome);
			disciplina.setCh(ch);
			disciplina.setCentro(centroControle.getCentro(centro_sigla));
			return dados.insertDisciplina(disciplina);
		}
		
	}
	
	public boolean updateDisciplina( int codigo, String nome, int ch, String centro_sigla) throws SQLException {
		
		Disciplina disciplina = null;
		
		if (codigo==0 || nome.isEmpty() || ch == 0 || centro_sigla.isEmpty()) {
			return false;
		} else {
			disciplina = new Disciplina();
			disciplina.setCodigo(codigo);
			disciplina.setNome(nome);
			disciplina.setCh(ch);
			disciplina.setCentro(centroControle.getCentro(centro_sigla));
			return dados.updateDisciplina(disciplina);
		}
		
	}
	
	public boolean deleteDisciplina(String matricula) throws SQLException {
		
		return dados.deleteDisciplina(matricula);		
		
	}
	
	public List<Disciplina> getDisciplinas(String nome) throws SQLException {
		
		return dados.getDisciplinas(nome);
		
	}
	
	public Disciplina getDisciplina(int matricula) throws SQLException {
		
		return dados.getDisciplina(matricula);
		
	}
	
	public List<Disciplina> getDisciplinas() throws SQLException {
		
		return dados.getDisciplinas();
		
	}

}
