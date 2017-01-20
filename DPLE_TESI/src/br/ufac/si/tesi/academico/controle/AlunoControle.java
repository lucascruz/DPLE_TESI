package br.ufac.si.tesi.academico.controle;

import java.sql.SQLException;
import java.util.List;

import br.ufac.si.tesi.academico.dados.AlunoDB;
import br.ufac.si.tesi.academico.dados.Conexao;
import br.ufac.si.tesi.academico.modelo.Aluno;

public class AlunoControle {
	
	private AlunoDB dados = null;
	private CursoControle cursoControle = null;
	
	public AlunoControle(Conexao conexao) {

		dados = new AlunoDB(conexao);
		cursoControle = new CursoControle(conexao);
		
	}
	
	public boolean insertAluno(int matricula, String nome, String telefone, String endereco, String cep, String email, String sexo,  boolean pne, int curso_codigo) {
		
		Aluno aluno = null;
		
		if (matricula == 0 || nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty() ||cep.isEmpty()  || email.isEmpty() || sexo.isEmpty() || pne == false || curso_codigo==0){
			return false;
		} else {
			aluno = new Aluno();
			aluno.setMatricula(matricula);
			aluno.setNome(nome);
			aluno.setFone(telefone);
			aluno.setEndereco(endereco);
			aluno.setCep(cep);
			aluno.setEmail(email);
			aluno.setSexo(sexo);
			aluno.setPne(pne);
			aluno.setCurso(cursoControle.getCurso(curso_codigo));
			return dados.insertAluno(aluno);
		}
		
	}
	
	public boolean updateAluno(int matricula, String nome, String telefone, String endereco, String cep, String email, String sexo,  boolean pne, int curso_codigo) throws SQLException{
		
		Aluno aluno = null;
		
		if (matricula == 0 || nome.isEmpty() || telefone.isEmpty() || endereco.isEmpty() ||cep.isEmpty()  || email.isEmpty() || pne==false || curso_codigo==0){
			return false;
		} else {
			aluno = dados.getAluno(matricula);
			aluno.setMatricula(matricula);
			aluno.setNome(nome);
			aluno.setFone(telefone);
			aluno.setEndereco(endereco);
			aluno.setCep(cep);
			aluno.setEmail(email);
			aluno.setSexo(sexo);
			aluno.setPne(pne);
			aluno.setCurso(cursoControle.getCurso(curso_codigo));
			return dados.updateAluno(aluno);
		}
		
	}
	
	public boolean deleteAluno(int matricula) {
		
		return dados.deleteAluno(matricula);		
		
	}
	
	public List<Aluno> getAlunos(String nome) throws SQLException {
		
		return dados.getAlunos(nome);
		
	}
	
	public Aluno getAluno(int matricula) throws SQLException {
		
		return dados.getAluno(matricula);
		
	}
	
	public List<Aluno> getAlunos() throws SQLException {
		
		return dados.getAlunos();
		
	}

}

