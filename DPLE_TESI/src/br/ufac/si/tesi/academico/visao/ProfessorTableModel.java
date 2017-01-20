package br.ufac.si.tesi.academico.visao;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufac.si.tesi.academico.modelo.Professor;

@SuppressWarnings("serial")
public class ProfessorTableModel extends AbstractTableModel {
	
	private List<Professor> lista;
	
	public ProfessorTableModel(List<Professor> lista) {

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

		Professor professor = lista.get(linha);
		Object obj = null;
		
		switch (coluna) {
		case 0:
			obj = professor.getMatricula();
			break;
		case 1:
			obj = professor.getNome();
			break;
		case 2:
			obj = professor.getRg();
			break;
		case 3:
			obj = professor.getCpf();
			break;
		case 4:
			obj = professor.getTelefone();
			break;
		case 5:
			obj = professor.getEndereco();
			break;
		case 6:
			obj = professor.getCep();
			break;
		case 7:
			obj = professor.getEmail();
			break;
		case 8:
			obj = professor.isSubstituto();
			break;
		case 9:
			obj = professor.getCentro().getSigla();
			break;

		default:
			break;
		}
		
		return obj;
	}

}
