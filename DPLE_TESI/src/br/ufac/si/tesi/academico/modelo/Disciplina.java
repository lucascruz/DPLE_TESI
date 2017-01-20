package br.ufac.si.tesi.academico.modelo;

public class Disciplina {
	private String nome,codigo;
	private int ch;
	private Centro centro;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getCh() {
		return ch;
	}
	public void setCh(int ch) {
		this.ch = ch;
	}
	public void setCentro(Centro centro){
		this.centro=centro;
	}
	public Centro getCentro(){
		return centro;
	}
}
