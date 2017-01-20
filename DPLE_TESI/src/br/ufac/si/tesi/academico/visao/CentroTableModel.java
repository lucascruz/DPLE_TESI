package br.ufac.si.tesi.academico.visao;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufac.si.tesi.academico.modelo.Centro;

@SuppressWarnings("serial")
public class CentroTableModel extends AbstractTableModel {
	
	private List<Centro> lista;
	
	public CentroTableModel(List<Centro> lista) {

		this.lista = lista;
		
	}

	@Override
	public int getColumnCount() {
		return 2;
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
			nome = "Sigla";
			break;
		case 1:
			nome = "Nome";
			break;

		default:
			break;
		}
		
		return nome;
		
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		Centro centro = lista.get(linha);
		Object obj = null;
		
		switch (coluna) {
		case 0:
			obj = centro.getSigla();
			break;
		case 1:
			obj = centro.getNome();
			break;
		default:
			break;
		}
		
		return obj;
	}

}
