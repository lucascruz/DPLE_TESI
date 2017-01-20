package br.ufac.si.tesi.academico.controle;

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
	
	public boolean insertDisciplina( String codigo, String nome, int ch, String centro_sigla) {
		
		Disciplina disciplina = null;
		
		if (codigo.isEmpty() || nome.isEmpty() || ch == 0 || centro_sigla.isEmpty()) {
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
	
	public boolean updateDisciplina( String codigo, String nome, int ch, String centro_sigla) {
		
		Disciplina disciplina = null;
		
		if (codigo.isEmpty() || nome.isEmpty() || ch == 0 || centro_sigla.isEmpty()) {
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
	
	public boolean deleteDisciplina(int matricula) {
		
		return dados.deleteDisciplina(matricula);		
		
	}
	
	public List<Disciplina> getDisciplinas(String nome) {
		
		return dados.getDisciplinaes(nome);
		
	}
	
	public Disciplina getDisciplina(int matricula) {
		
		return dados.getDisciplina(matricula);
		
	}
	
	public List<Disciplina> getDisciplinas() {
		
		return dados.getDisciplinaes();
		
	}

}
