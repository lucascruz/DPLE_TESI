package br.ufac.si.tesi.academico.controle;

import java.sql.SQLException;
import java.util.List;

import br.ufac.si.tesi.academico.dados.CursoDB;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Curso;

public class CursoControle {

	private CursoDB dados = null;

	public CursoControle(Conexao conexao) {

		dados = new CursoDB(conexao);

	}

	public boolean insertCurso(int codigo, String nome, int coordenador) {

		Curso curso = null;

		if (codigo == 0 || nome.isEmpty() || coordenador== 0) {
			return false;
		} else {
			curso = new Curso();
			curso.setCodigo(codigo);
			curso.setNome(nome);
			curso.setCoordenador(coordenador);
			return dados.insertCurso(curso);
		}

	}

	public boolean updateCurso(int codigo, String nome, int coordenador) {

		Curso curso = null;

		if (codigo == 0 || nome.isEmpty() || coordenador== 0) {
			return false;
		} else {
			curso = new Curso();
			curso.setCodigo(codigo);
			curso.setNome(nome);
			curso.setCoordenador(coordenador);
			return dados.updateCurso(curso);
		}
	}

	public boolean deleteCurso(int matricula) {

		return dados.deleteCurso(matricula);		

	}

	public List<Curso> getCursos(String nome) throws SQLException {

		return dados.getCursos(nome);

	}

	public Curso getCurso(int matricula) {

		return dados.getCurso(matricula);

	}

	public List<Curso> getCursos() throws SQLException {

		return dados.getCursos();

	}

}
