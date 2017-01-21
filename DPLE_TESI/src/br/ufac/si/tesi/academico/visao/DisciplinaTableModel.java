package br.ufac.si.tesi.academico.visao;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufac.si.tesi.academico.modelo.Disciplina;

public class DisciplinaTableModel extends AbstractTableModel {
	
	private List<Disciplina> lista;
	
	public DisciplinaTableModel(List<Disciplina> lista) {

		this.lista = lista;
		
	}

	@Override
	public int getColumnCount() {
		return 4;
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
			nome = "Codigo";
			break;
		case 1:
			nome = "Nome";
			break;
		case 2:
			nome = "CH";
			break;
		case 3:
			nome = "Sigla Centro";
			break;

		default:
			break;
		}
		
		return nome;
		
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		Disciplina disciplina = lista.get(linha);
		Object obj = null;
		
		switch (coluna) {
		case 0:
			obj = disciplina.getCodigo();
			break;
		case 1:
			obj = disciplina.getNome();
			break;
		case 2:
			obj = disciplina.getCh();
			break;
		case 3:
			obj = disciplina.getCentro().getSigla();
			break;
		default:
			break;
		}
		
		return obj;
	}

}

