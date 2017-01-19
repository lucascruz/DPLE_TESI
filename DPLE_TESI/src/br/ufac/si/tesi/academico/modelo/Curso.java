package br.ufac.si.tesi.academico.modelo;
public class Curso {
	private String nome;
	private String codigo;

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

	@Override
	public String toString(){
		return this.nome;
	}
}