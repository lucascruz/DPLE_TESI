package br.ufac.si.tesi.academico.modelo;

public class Aluno{
	
	private String nome,endereco,fone, cep, email, sexo;
	private int matricula;
	private boolean pne;
	private Curso curso;
	  
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public  String getSexo() {
		return sexo;
	}
	public void setSexo(String s) {
		this.sexo = s;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setPne(boolean pne){
		this.pne = pne;
	}
	
	public boolean isPne() {
		return pne;
	}
	@Override
	public String toString() {

		return this.nome;
		
	}

}