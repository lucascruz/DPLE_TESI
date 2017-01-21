package br.ufac.si.tesi.academico.controle;

import java.sql.SQLException;
import java.util.List;


import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.dados.CursoDB;

import br.ufac.si.tesi.academico.modelo.Curso;

public class CursoControle {
private CursoDB dados = null;
private ProfessorControle professorControle = null;
	
	public CursoControle(Conexao conexao) {

		dados = new CursoDB(conexao);
		professorControle = new ProfessorControle(conexao);
		
	}
	
	public boolean insertCurso(int codigo, String nome, int coordenador) throws SQLException {
		
		Curso curso= null;
		
		if (codigo ==0|| nome.isEmpty() || coordenador==0) {
			return false;
		} else {
			curso = new Curso();
			curso.setCodigo(codigo);
			curso.setNome(nome);
			curso.setProfessor(professorControle.getProfessor(coordenador));
			return dados.insertCurso(curso);
		}
		
	}
	
	public boolean updateCurso(int codigo, String nome, int coordenador) throws SQLException {
		
		Curso curso = null;
		
		if (codigo==0 || nome.isEmpty() || coordenador ==0) {
			return false;
		} else {
			curso = dados.getCurso(codigo);
			curso.setNome(nome);
			curso.setProfessor(professorControle.getProfessor(coordenador));
			return dados.updateCurso(curso);
		}
		
	}
	
	public boolean deleteCurso(int codigo) throws SQLException {
		
		return dados.deleteCurso(codigo);		
		
	}
	
	public List<Curso> getDisciplinas(String nome) throws SQLException {
		
		return dados.getCursos(nome);
		
	}
	
	public Curso getCurso(int codigo) throws SQLException {
		
		return dados.getCurso(codigo);
		
	}
	
	public List<Curso> getCursos() throws SQLException {
		
		return dados.getCursos();
		
	}
}
