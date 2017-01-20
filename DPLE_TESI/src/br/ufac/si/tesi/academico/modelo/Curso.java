package br.ufac.si.tesi.academico.modelo;
public class Curso {
	private String nome;
	private int codigo, coordenador;
	public int getCoordenador() {
		return coordenador;
	}
	public void setCoordenador(int coordenador) {
		this.coordenador = coordenador;
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
}