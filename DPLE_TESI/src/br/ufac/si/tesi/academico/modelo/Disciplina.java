package br.ufac.si.tesi.academico.modelo;

public class Disciplina {
	private String nome;
	private int ch, codigo;
	private Centro centro;
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
	@Override
	public String toString() {

		return this.nome;
		
	}
}
