package br.ufac.si.tesi.academico.visao;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufac.si.tesi.academico.modelo.Aluno;

public class AlunoTableModel extends AbstractTableModel {
	
	private List<Aluno> lista;
	
	public AlunoTableModel(List<Aluno> lista) {

		this.lista = lista;
		
	}

	@Override
	public int getColumnCount() {
		return 10;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}
	
	@Override
	public String getColumnName(int coluna) {

		String nome = null;
		
		switch (coluna) {
		case 0:
			nome = "Matrícula";
			break;
		case 1:
			nome = "Nome";
			break;
		case 2:
			nome = "RG";
			break;
		case 3:
			nome = "CPF";
			break;
		case 4:
			nome = "Telefone";
			break;
		case 5:
			nome = "Endereço";
			break;
		case 6:
			nome = "CEP";
			break;
		case 7:
			nome = "E-mail";
			break;
		case 8:
			nome = "Substituto";
			break;
		case 9:
			nome = "Centro";
			break;

		default:
			break;
		}
		
		return nome;
		
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		Aluno aluno = lista.get(linha);
		Object obj = null;
		
		switch (coluna) {
		case 0:
			obj = aluno.getMatricula();
			break;
		case 1:
			obj = aluno.getNome();
			break;
		case 2:
			obj = aluno.getFone();
			break;
		case 3:
			obj = aluno.getEndereco();
			break;
		case 4:
			obj = aluno.getCep();
			break;
		case 5:
			obj = aluno.getEmail();
			break;
		case 6:
			obj = aluno.getSexo();
			break;
		case 7:
			obj = aluno.isPne();
			break;
		case 8:
			obj = aluno.getCurso().getCodigo();
			break;

		default:
			break;
		}
		
		return obj;
	}

}
