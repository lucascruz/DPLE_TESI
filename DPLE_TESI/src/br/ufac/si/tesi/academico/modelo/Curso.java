package br.ufac.si.tesi.academico.modelo;

import java.math.BigDecimal;

public class Curso {
	private String nome;
	private int coordenador, codigo;
	private Professor professor;
	public int getCoordenador() {
		return coordenador;
	}
	public void setCoordenador(Professor p) {
		this.setProfessor(p);
	}


	private Disciplina disciplina;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Disciplina getDisciplina(){
		return disciplina;
	}
	
	public void setDisciplina (Disciplina disciplina){
		this.disciplina = disciplina;
	}
	

	@Override
	public String toString(){
		return this.nome;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor profe) {
		this.professor = profe;
	}
}